// C  program for Dijkstra's shortest path algorithm for adjacency
// list representation of graph
//
//author :Charan

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <stdbool.h>
// A utility function to create a new adjacency list node
struct AdjListNode* newAdjListNode(int dest, int weight);
// A utility function that creates a graph of V vertices
struct Graph* createGraph(int V);
// Adds an edge to an undirected graph
void addEdge(struct Graph* graph, int src, int dest, int weight);
// A utility function to swap two nodes of min heap. Needed for min heapify
// A utility function to create a new Min Heap Node
struct MinHeapNode* newMinHeapNode(int v, int dist);
// A utility function to create a Min Heap
struct MinHeap* createMinHeap(int capacity);
// A utility function to swap two nodes of min heap. Needed for min heapify
void swapMinHeapNode(struct MinHeapNode** a, struct MinHeapNode** b);
// A standard function to heapify at given idx
// This function also updates position of nodes when they are swapped.
// Position is needed for decreaseKey()
void minHeapify(struct MinHeap* minHeap, int idx);
// A utility function to check if the given minHeap is ampty or not
int isEmpty(struct MinHeap* minHeap);
// Standard function to extract minimum node from heap
struct MinHeapNode* extractMin(struct MinHeap* minHeap);
// Function to decreasy dist value of a given vertex v. This function
// uses pos[] of min heap to get the current index of node in min heap
void decreaseKey(struct MinHeap* minHeap, int v, int dist);
bool isInMinHeap(struct MinHeap *minHeap, int v);
void printArr(int dist[], int n);
float* dijkstra(struct Graph* graph, int src);
int **allocate_board(struct Graph* graph,int Rows, int Cols);
void AddReq(int source,int destn,int begin_interval,int end_interval,int requestno);
void printReq(int i);
void sortReq(int first,int last);
void findTaxi(int** loc, int rows, int cols,int* count,int reqs);
