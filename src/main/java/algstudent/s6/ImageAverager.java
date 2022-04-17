package algstudent.s6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ImageAverager {

	private Image real_img, bad_img; // to store the main good and main bad image
	private Image avg_img, half1_img, half2_img; // to store the final tests to see if we improve the previous results
	private Image[] dataset; // dataset to store all the images (good and bad ones)
	private int[] sol; // to store the partial results (where I am putting the pictures? 0->not
						// assigned, 1->first half, 2->second half
	private int[] bestSol; // to store the best solution
	private int width, height; // to store the width and height of the image
	// backtracking variables
	private int counter; // to store the number of times we assign an image to half1, half2 or no group
	private double max_zncc = -1; // to store the best ZNCC
	private int nImgs;
	private Random rand;
	private int ngroup1 = 0;
	private int ngroup2 = 0;

	/**
	 * Constructor
	 * 
	 * @real_path path to the real image (pattern to find) on disk
	 * @bad_path path to the bad image on disk
	 * @n_real number of real images in the dataset (>= 1)
	 * @n_bad number of bad images in the dataset
	 * @s_noise standard deviation for noise
	 */
	public ImageAverager(String real_path, String bad_path, int n_real, int n_bad, double s_noise) {
		assert (n_real >= 1) && (n_bad < n_real);// assert at least one reference image

		// load reference and bad images
		this.real_img = new Image(real_path);
		this.bad_img = new Image(bad_path);
		this.width = this.real_img.getWidth();
		this.height = this.real_img.getHeight();
		nImgs = n_real + n_bad;
		rand = new Random();

		// create the dataset as an array of unordered randomly chosen real and bad
		// images
		int total_imgs = n_real + n_bad; // the total number of images are the good + the bad ones
		this.dataset = new Image[total_imgs]; // the data set for the total of images
		this.sol = new int[total_imgs]; // we will use this variable during the process 0->not assigned, 1->first half,
										// 2->second half
		this.bestSol = new int[total_imgs]; // we will use this variable to store the best results
		int[] rand_index = this.randomIndexes(total_imgs); // random array of positions to mix images
		Image hold_img; // temp images
		int region = 0; // 0-up, 1-down, 2-left, 3-right
		for (int i = 0; i < n_real; i++) { // to save good images
			hold_img = new Image(this.width, this.height); // generate image
			hold_img.addSignal(this.real_img); // save the image
			hold_img.suppressRegion(region); // a half part of the image is deleted
			hold_img.addNoise(s_noise); // add some noise
			this.dataset[rand_index[i]] = hold_img; // save image
			if (region == 3)
				region = 0;
			else
				region++;
		}
		region = 0;
		for (int i = n_real; i < n_real + n_bad; i++) { // to save bad images
			hold_img = new Image(this.width, this.height); // generate image
			hold_img.addSignal(this.bad_img); // save the image
			hold_img.invertSignal(); // corrupt the image
			hold_img.suppressRegion(region); // the fourth part of the image is deleted
			hold_img.addNoise(s_noise); // add some noise
			this.dataset[rand_index[i]] = hold_img; // save image
			if (region == 3)
				region = 0;
			else
				region++;
		}
	}

	/**
	 * To generate a random array of positions
	 * 
	 * @param n Length of the array
	 * @return
	 */
	public int[] randomIndexes(int n) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(i);
		Collections.shuffle(list);
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = list.get(i);
		return array;
	}

	/**
	 * Store resulting images for testing
	 * 
	 * @out_dir directory save the output images
	 */
	public void saveResults(String out_dir) {
		this.avg_img.save(out_dir + "/img_avg.png");
		this.half1_img.save(out_dir + "/img_half1_avg.png");
		this.half2_img.save(out_dir + "/img_half2_avg.png");
		for (int i = 0; i < this.dataset.length; i++) {
			this.dataset[i].save(out_dir + "/img_" + i + "_klass_" + this.bestSol[i] + ".png");
		}
	}

	/**
	 * @return the number of steps carried out by the algorithm to solve the problem
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Computes the ZNCC between both image dataset halves
	 * 
	 * @return the computed ZNCC
	 */
	public double zncc() {
		return this.half1_img.zncc(this.half2_img);
	}

	/**
	 * Greedy algorithm: random instances for each half, the best one is the final
	 * solution
	 * 
	 * @n_tries number of random tries
	 */
	public void splitSubsetsGreedy(int n_tries) {
		counter = 0;
		for (int n = 0; n < n_tries; n++) {
			int[] randomIndexes = randomIndexes(nImgs);

			this.half1_img = new Image(this.width, this.height);
			this.half2_img = new Image(this.width, this.height);

			for (int i = 0; i < nImgs; i++) {
				randomIndexes[i] = rand.nextInt(3);
				if (randomIndexes[i] == 1) {
					this.half1_img.addSignal(this.dataset[i]);
				} else if (randomIndexes[i] == 2) {
					this.half2_img.addSignal(this.dataset[i]);
				}
			}

			this.avg_img = new Image(this.width, this.height);
			this.avg_img.addSignal(this.half1_img);
			this.avg_img.addSignal(this.half2_img);

			double zncc = zncc();
			sol = randomIndexes;
			if (this.max_zncc < zncc) {
				this.max_zncc = zncc;
				bestSol = randomIndexes.clone();
				this.avg_img = new Image(this.width, this.height);
				this.avg_img.addSignal(this.half1_img);
				this.avg_img.addSignal(this.half2_img);
			}

			counter++;
		}
	}

	/**
	 * Backtracking algorithm
	 * 
	 * @max_unbalancing: (pruning condition) determines the maximum difference
	 *                   between the number of images on each half set
	 */
	public void splitSubsetsBacktracking(int max_unbalancing) {
		this.half1_img = new Image(this.width, this.height);
		this.half2_img = new Image(this.width, this.height);

		counter = 0;
		max_zncc = -1;
		ngroup1 = 0;
		ngroup2 = 0;
		recBacktrackingBalanced(0, max_unbalancing);
	}

	private void recBacktrackingBalanced(int level, int max_unbalancing) {
		if (level == nImgs) {
			var temp_half1_img = new Image(this.width, this.height);
			var temp_half2_img = new Image(this.width, this.height);
			for (int i = 0; i < nImgs; i++) {

				if (sol[i] == 1) {
					temp_half1_img.addSignal(this.dataset[i]);
				} else if (sol[i] == 2) {
					temp_half2_img.addSignal(this.dataset[i]);
				}
			}

			double zncc = temp_half1_img.zncc(temp_half2_img);
			if (this.max_zncc < zncc) {
				this.max_zncc = zncc;
				bestSol = sol.clone();
				this.avg_img = new Image(this.width, this.height);
				this.half1_img = temp_half1_img;
				this.half2_img = temp_half2_img;
				this.avg_img.addSignal(this.half1_img);
				this.avg_img.addSignal(this.half2_img);

			}
		} else {
			counter++;

			ngroup1++;
			if (max_unbalancing >= ngroup1 - ngroup2) {
				sol[level] = 1;
				recBacktrackingBalanced(level + 1,max_unbalancing);
			}
			ngroup1--;

			ngroup2++;
			if (max_unbalancing >= ngroup2 - ngroup1) {
				sol[level] = 2;
				recBacktrackingBalanced(level + 1,max_unbalancing);
			}
			ngroup2--;

			sol[level] = 0;
			recBacktrackingBalanced(level + 1,max_unbalancing);
		}
	}

	/**
	 * Backtracking algorithm without balancing. Using a larger than the number of
	 * images in the dataset ensures no prunning
	 */
	public void splitSubsetsBacktracking() {

		this.half1_img = new Image(this.width, this.height);
		this.half2_img = new Image(this.width, this.height);

		counter = 0;
		max_zncc = -1;
		recBacktrackingUnbalanced(0);
	}

	private void recBacktrackingUnbalanced(int level) {
		if (level == nImgs) {
			var temp_half1_img = new Image(this.width, this.height);
			var temp_half2_img = new Image(this.width, this.height);
			for (int i = 0; i < nImgs; i++) {

				if (sol[i] == 1) {
					temp_half1_img.addSignal(this.dataset[i]);
				} else if (sol[i] == 2) {
					temp_half2_img.addSignal(this.dataset[i]);
				}
			}

			double zncc = temp_half1_img.zncc(temp_half2_img);
			if (this.max_zncc < zncc) {
				this.max_zncc = zncc;
				bestSol = sol.clone();
				this.avg_img = new Image(this.width, this.height);
				this.half1_img = temp_half1_img;
				this.half2_img = temp_half2_img;
				this.avg_img.addSignal(this.half1_img);
				this.avg_img.addSignal(this.half2_img);

			}
		} else {
			counter++;

			sol[level] = 1;
			// this.half1_img.addSignal(this.dataset[level]);
			recBacktrackingUnbalanced(level + 1);
			// this.half1_img.deleteSignal(this.dataset[level]);

			sol[level] = 2;
			// this.half2_img.addSignal(this.dataset[level]);
			recBacktrackingUnbalanced(level + 1);
			// this.half2_img.deleteSignal(this.dataset[level]);

			sol[level] = 0;
			recBacktrackingUnbalanced(level + 1);
		}
	}

}
