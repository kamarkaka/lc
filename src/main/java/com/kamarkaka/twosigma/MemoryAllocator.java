package com.kamarkaka.twosigma;

/***
 * Implement a simple memory allocator.
 * For simplification, we use an integer array to represent a contiguous memory space.
 * Use array index as memory address.
 *    Implement malloc(int size), which returns a pointer into the memory array to a chunk of at least size size, and free(int ptr), which release the memory at ptr for future allocation.
 *    Implement in-place header in each memory chunk. No additional O(n) data structure (aside from the data array) is allowed. See how this works in the next sections.
 *    In malloc(int size) function
 *       Implement simple linear search approach to find the first available free chunk to allocate. Time complexity of this will be O(n).
 *       Implement memory chunk splitting. When selecting a larger size memory chunk for allocation, splits it into one allocated chunk and one free chunk. If the size of leftover space after splitting <= header size and becomes unallocatable, we will pad this small space back to the newly allocated memory chunk.
 *    In free(int ptr) function
 *       Implement memory chunk coalescing. On freeing a memory chunk, we can do the opposite to splitting operation, and coalesce two (or more) adjacent free chunks to a larger one.
 *       In this question, you can always expect a valid ptr will be passed to free function, so no need to validate or handle invalid address.
 *
 * How Simple Memory Allocator Works
 * We will use an integer array to represent a fixed size memory space. As a result, array index will be used as the memory address. We will use -1 to represent NULL address.
 * The memory space is divided into contiguous memory chunks. Each chunk also carries a header. The header includes the following fields, each occupies one integer in the array.
 *    size: size of the entire chunk.
 *    status: ALLOCATED or FREE. For example, we can use 1 to indicate the chunk is allocated, 0 to indicate the chunk is free.
 *    prev chunk address: points to the prev chunk in the chunks list. Here we don't need to maintain a next chunk addr, because it can be easily calculated as (current chunk addr + size of current chunk).
 * By using the header to get prev/next chunk addr, we can search through the list of chunks in both direction starting from any given chunk.
 * Essentially we have a doubly linked list implemented "in-place".
 *
 */
public class MemoryAllocator {
}
