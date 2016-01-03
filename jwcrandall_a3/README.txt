Programed by Joseph Crandall
last modifed 11/29/15

Implementation of
	the naive algorithm
	the greedy algorithm
	and the annealing algorithm
for the traveling salesman problem

A "naive" algorithm in Naive.java that merely divides the points more or less evenly among the salesmen ("more or less" because m may not exactly divide the number of points).

What is "greedy" about the greedy algortihm?
At each step, each salesman adds a new point to their existing tour.
The new point is selected based on how close it is to previous point.
Greedy => no backtracking.

Greedy.java OrderNotation Inluded in GreedyOrder.txt