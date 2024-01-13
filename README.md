Name: Daniel Oswaldo Perez
uni: dop2107
Course: Data Structures and Algorithms taught by Dr. Brian Borowski

Online Resources Used:

Exception Handling Video by "Coding with John" on YouTube:
https://www.youtube.com/watch?v=1XAfapkBQjk&t=635s

Reading from file tutorial:
https://www.baeldung.com/reading-file-in-java

Using iterator tutorial:
https://www.tutorialspoint.com/java/java_using_iterator.html

Expectations: I expect the hashmap to be the fastest due to the O(1) retrival and insertion times, unless there is re-sizing of course.
I believe that the bst will be the slowest since it is not automatically balanced as the avl tree is, so the retrival and search times will be more than O(lg(n)).

run times (in seconds)for java AnagramFinder least words.txt <hash|bst|avl>:
hashmap:
1)0.881
2)0.610
3)0.598
4)0.599
5) 0.585
Average) 0.6546

bst:
1)2.045
2)1.954
3)1.974
4)4.312
5)1.975
Average)2.452

avl:
1)0.962
2)0.975
3)0.983
4)0.970
5)0.972
Average)0.9724

Yes, the timed results match what I had with the hashmap being the fastest, the avl being the second fastest, and the bst being the slowest. This is because of the average time complexity of insertions and retrivals into hashmaps of O(1) when collisions are managed well. In the code for Hashmap, seperate chaining works as our collision resolution technique, which provides an efficient time complexity for our purposes. The bst map performed the worst because we are not updating the tree to be balance with each insertion like we are with AVL, so we get an insertion time somwehere between theta(lg(n)) and theta(n). Since AVL trees guarantee logarithmic height, we know that it will be better for insertion and retrieval than a bst, but it does not have the speed of a hashmap. That being said, the insertion and retrival times are both O(lg(n)).

