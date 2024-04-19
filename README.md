# Lights Out Problem

## 📜 Project Info

* Java : version 22
* UI : Javax

## 💬 How to run ?

When you launch the project, you will initially see the menu page where you can select the desired method to solve the
problem. The available options are Backtrack, BFS, and IDA*. The default mode is set to Backtrack.

Next, you need to choose your plaintext file. Afterward, a frame will display the initial board along with the pieces
that need to be played in the game. After a few seconds, another frame will appear containing the solution to the
problem.

## 🔥 About the solutions

To solve the main problem without any changes, linear algebra is employed! It's an effective approach because we
understand
precisely how each action will react, and changes that occur are easily identifiable. Changes are limited to the
neighbors of each element. However, this isn't the case for this variant. If only change was adding the depth! We could
still use
the original method because we don't perform the XOR; instead, we simply make changes according to the mode of the given
depth.

What we attempted was to solve the problem we faced using backtracking as the first method! We began by placing the
pieces from position (0,0) and continued until we achieved a completely dark board with no lights visible. After that,
the question arose: how can we enhance the efficiency of backtracking to make it faster? The only effective way is to
optimize memory usage because, in this type of solution, we can still encounter the worst-case scenario. Regardless of
our efforts, the time complexity remains significant!

the backtrack give us the answer until 09.text sample file !! for 09.text and 10.text no, it takes too much time.

After that we tried with the bfs!

### Advantages of Breadth First Search:

* Guaranteed Exploration: BFS will never become trapped in a useless path indefinitely.
* Solution Assurance: If a solution exists, BFS will certainly find it.
* Optimal Solution Discovery: If multiple solutions are available, BFS can identify the minimal one requiring the fewest
  steps.
* Low Storage Requirements: Storage needs are linear in relation to depth.
* Ease of Implementation: BFS is straightforward to program.

It is more memory-efficient than DFS, but for this problem the queue grows rapidly, which in some cases may lead to a
heap error. While
it is faster than backtracking, it is not sufficiently effective for all scenarios.

but A* and IDA* is better than BFS even !

### IDA*:

Iterative Deepening A* (IDA*) is a graph traversal and path-finding technique designed to determine the shortest path in
a weighted graph between a defined start node and any one of a set of goal nodes. It combines the principles of
iterative deepening depth-first search with the A* search algorithm's approach of using a heuristic function to estimate
the remaining cost to reach the goal.

IDA* is essentially a memory-limited version of A*. While performing all the operations of A* and maintaining the
ability to find the optimal shortest path, it requires significantly less memory. Unlike Iterative Deepening Depth-First
Search (DFS), which uses simple depth to decide when to end the current iteration and proceed at a deeper level,
Iterative Deepening A* employs a heuristic to select which nodes to explore and at which depth to halt.

Src: [geeksforgeeks](https://www.geeksforgeeks.org/iterative-deepening-a-algorithm-ida-artificial-intelligence/)

so this is much better than the other solution ! and we solve the 09.text ... 


