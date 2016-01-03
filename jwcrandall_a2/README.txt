READ ME

pseudocode for FilterTreeRectIntersection



now scan rectangles in second set and query agaisnt tree
		for i = - to m
		get list of intersectons from tree
		LinkedList intersectionSet = filterTreeSearch(rectSet2[i]);
		if intersectionSet not empty
			numIntersection += intersectoinSet.size();
			Place intersections in in intersectionSet into list of Intersections
		end if
		end for
		return all intersections
