### CSC320 Fall 21 Programming Project Sorting
#### Comparative Study of Sorting Techniques

- Part I due Monday, October 25 end-of-day

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
