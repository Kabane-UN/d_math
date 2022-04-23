package main

import (
	"fmt"
)

var arr []int = []int{4, 2, 5, 1, 3, 200, -1, 13, 43, 45}

func swap(i, j int) {
	c := arr[i]
	arr[i] = arr[j]
	arr[j] = c
}
func less(i, j int) bool {
	if arr[i] < arr[j] {
		return true
	} else {
		return false
	}

}
func qsort(n int,
	less func(i, j int) bool,
	swap func(i, j int), optional_m ...int) {
	m := 0
	if len(optional_m) > 0 {
		m = optional_m[0]
	}
	if n-m <= 1 {
		return
	}
	split := 0
	pivot := m
	left := m
	right := n - 1

	for {
		for ; less(left, pivot); left++ {
		}
		for ; less(pivot, right); right-- {
		}

		if left >= right {
			split = right
			break
		}
		if left == pivot {
			pivot = right
		} else if right == pivot {
			pivot = left
		}
		swap(left, right)
	}
	qsort(split, less, swap, m)
	qsort(n, less, swap, split+1)

}

func main() {
	qsort(len(arr), less, swap)
	fmt.Println(arr)

}
