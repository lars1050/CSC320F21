### CSC320 Fall 21 Programming Project Sorting
#### Comparative Study of Sorting Techniques

- Part I due Monday, October 25 end-of-day
- Part II due Monday, November 8 end-of-day
- Part III due Monday, December 6 end-of-day

Zip up your report, code, and data file for submission.

<hr>

PART III

### Writing a Report

Create a report to show your results and to explain their significance. It is not sufficient to just report your results. Include the limits that you determined for your particular machine. Make note of any increases in Heap or Stack memory when running experiments.

Present your data using graphs. You can use any graphine application that you would like (Excel, Python, R, Matlab, etc.). For experiments on random datasets, it is best to calculate an average of the 3 runs, which can probably easily be done with your graphing application. Mix and match results as you wish. I would expect to see at least 4 graphs corresponding to the 4 sets of experiments below. But, as I said, mix and match as you wish, so your 4 graphs (or 8 graphs) might present data grouped in a completely different way.

> Each graph should have a minimum of 2 lines representing some comparison. Please do not graph the elapsed time and number of operations on the same graph because the y-axis cannot represent both time and operations. You might think of a clever way to normalize the results so that the y-axis is representative of both, but otherwise you should create different graphs. The requirements are that every experiment have at leaast 10 array sizes, thus you should have at least 10 points to use to graph an interpolated line (your graphing program is doing the interpolation for you). 

Write at least 3 paragraphs that analyze some of your findings. An analysis should give a thoughtful explanation for any differences and similarities that you observe in the data. You can focus on the same algorithm applied to different array configurations, or you can focus on different algorithms applied to the same array configuration, or some other aspect that is of interest to you. The language should have a professional tone.

Here is an example of an analysis of the data that also refers to the graphs (which aren't shown here -- just imagine that they are here):

<ul><i>Consider the test of SelectionSort that sorted an array of size 1000 and returned an iteration count of 998,900. The runtime of SelectionSort with respect to n is expressed as `f(n) = c1*n^2 - c1*n + c2`. However, for our purposes, we have eliminated all constants and are focused on the number of iterations, so really it is `f(n) = n^2 - n`. With an array of size 1000, we would expect SelectionSort to return an iteration count of 999,000. This is close to the results, thus we conclude that the code is working as expected and the runtime function is correct.</i><br><br>

<i>Looking at Graph #1 of results for Selection and Insertion Sort, we see that the runtime is the same for Selection sort regardless of the configuration of the input, and that as n increases, we are seeing a similarly shaped curve for all configurations. That is expected for Selection Sort because its worst- and best-case runtime are the same, namely Theta(n^2). This is in contrast to the results for Insertion sort, which show significant variability in the results across the different input configurations, demonstrating the range of runtime bounds from Omega(n) to O(n^2). And notice that the graph of runtimes for a sorted array with increasing n (see the blue line) is linear, which corresponds to the best-case Omega(n) bound of Insertion sort, whereas runtimes for a reverse sorted array (see green line) matches that of selection sort, which corresponds to the worst-case O(n^2) bound for Insertion Sort." 
</i></ul>

When you put your report together, remember to:
- Give your report a title
- Put your name on it
- Embed the graphs in the report
- Label your graphs (and tables if you use them).
- Include at least 3 paragraphs to explain your results. Be sure to refer to the graphs/tables when you are explaining the results.

Use informative legends and keys in your graphs. I should be able to look at the graph and the legend and understand the results.

IN YOUR REPORT, be sure to include the limits that you determined for your machine, so take notes while you are experimenting with your limits. TRUST ME -- you will be fiddling with a lot of stuff and you want to write down what you have done while you are experimenting so you can remember what you did.

<hr>

<h3>RUNNING EXPERIMENTS</h3>

Please compare the following algorithms using the specified datasets, by sorting arrays of increasing size. Please set the variables in DataMaker to work within the limits of your machine. Record the number of operations and elapsed time.

For every algorithm+array combination, gather data on 10 different sizes.

1. Report on Insertion Sort being applied to various data, including:
	- Random arrays of Integers (take the average of 3)
	- Sorted array of Integers
	- Reverse sorted array of Integers

2. Report on applying Quick Sort to various data, including:
	- Sorted Integers
	- Simples: When you create the data, sort it byNumeric. When you sort it with Quick sort, sort it byAlpha.
	- LARGE: This means very large numbers.

3. Report on Merge Sort combined with Insertion Sort at various size limits, including:
	- Sorted Integers, use Insertion sort with subarrays <= 1000.
	- Sorted Integers, use Insertion sort with subarrays <= 100.
	- Sorted Integers, but do not use Insertion sort (set the size limit = 0)

4. Counting versus Radix on random LARGE arrays.

If there are other experiments that you would rather run, send an email to Dr. Larson with changes that you would make to the above experiments. You can even add a different algorithm if you want. If approved, you can run your proposed experiments instead.

- For #1, you are collecting 10 sizes * 2 sorted types + 10 sizes * 3 random = 50 experiments.
- For #2, you are collecting 10 sizes * 3 data types = 30 experiments
- For #3, you are collecting 10 sizes * 3 variations = 30 experiments

That's a lot of data!

<hr>

PART II

> Deliverables: Essentially, I am looking for evidence that you are making consistent progress towards the final project. More specifically, you understand the code and you are using it to practice setting up an experiment. You know the limits of your machine. You have run some test runs in which you have the results saved to a file. You have a system for plotting the data. You will turn in a report on the limits, code to write to a file, and a graph of 1 experiment (e.g. Insertion sort applied to 10 different sized arrays of random Integers).

Now that you have several sorting algorithms complete, you can run experiments to compare their number of operations and runtime. As you gather data, make sure you check the number of operations and runtime to ensure that those values make sense. (You don't want to collect a lot of data only to discover there was an error, then have to collect it all again. For example, on my machine, it seems like the first time I run a sorting algorihtm, the elapsed time is too big.)

Remember that runtime efficiency is expressed as a limit as the size of n increases to infinity. When algorithms have different limits (bounds), there might be little difference between their runtimes when n is small, but the difference in runtime should grow as n grows. Therefore, we want to experiment with *large arrays* to really highlight the differences in efficiency in these algorithms.

Several files have been created to help you set up these experiments.
- DataIO.java manages the creation of data files and reading those files.
- Experiment has examples of making data files and running experiments.
- TimedSorting is the primary experiment driver. It has a series of functions called _experiment_ with different parameters depending on which type of algorithm to use in the experiment. The idea is that you create a TimedSorting object that will operate on a single data file. Then you can run experiments on that data using different algorithms and parameters.

You need to experiment a bit with your memory and the size of your datasets that you can handle. Here is an example of variables controlling sizes:

```
// set the range of values for DataMaker (these are public static variables)
DataIO.max = 10000000;
DataIO.minLarge = 100000000;
DataIO.maxDelta =  99999999;

// create the file with 10000000 array elements
DataIO.makeInteger("integer-random1.txt", 10000000, DataIO.Config.RANDOM);
```

First, determine limits (without memory expansion) on your machine. On my desktop (which is pretty powerful), it could easily handle 100,000,000 elements for Merge and Counting, but it ran out of HEAP memory at 1,000,000,000 elements. It quickly ran out of memory for Counting sort when my LARGE numbers were in the trillions. Quicksort will gobble up your STACK memory. Experiment with the numbers and determine where your memory limits are.

Now you need to expand your memory to see if you can stretch those limits.

To expand HEAP to 4 Gigs and STACK to 1 Gig, respectively, use these command line commands to run "main":

```
java -Xmx4G main
java -Xss1G main
```

Or in Netbeans, you can set command line arguments in "Run>Set Project Configuration>Customize". In the Arguments box, add "-Xmx4G". I think these are the max values that you can expand to.

<hr>

PART I

> NOTE: Do not wait until Sunday October 24th to do this assignment. Start it now, then you can come to office hours to ask questions, ask questions in class, and attend drop-in tutoring to get some help.

1. Complete the static sort method for SortQuick.

	- Use the textbook logic for Randomized Quicksort with 1 modification: **Select 3 random numbers and swap the middle value to be the pivot.**

	- Record the number of comparisons and return that value.

	- Test your results by copying what I have done in Main.

2. Complete the static sort method in SortRadix.java.

	- Use the textbook for the logic.

	- Be sure to increment the operations counter for each loop iteration. You will have to get the number of operations from Counting Sort and add to a total.

	- Test your results.

3. Modify the sort algorithm in SortMerge and SortInsertion.

	- Create a sorting algorithm that combines Merge and Insertion. In Merge Sort, continually divide the list in half until the subarray size is 64 elements or smaller. At that point, use Insertion sort to sort the subarray.

	- Be sure to increment the operations counter for each comparison and to include the operations from the call to Insertion Sort.

	- Test your results. NOTE: You should create an array that is larger than the subarray limit. One thing that might make this easier is if you change the subarray size to something like 10 while you are testing and make an array of size 50 and fill it with random numbers.

Note: The algorithms are sorting generic objects, which is why, even if you are using integers, they must be of type _Integer_, not int. For testing, I have provided a Simple object with comparators. For now, get the algorithms working with Integers. Soon, I will provide a means to create test data of type Integer and Simple so you can fully test your code.

Coming soon: details on Part II The Experiments.
