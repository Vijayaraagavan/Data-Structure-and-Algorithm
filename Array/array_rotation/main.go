package main

import "fmt"

func array_rot(arr []int, d, n int) []int {
	var res []int
	res = append(arr[d:], arr[0:d]...)
	return res
}

func main() {
	var arr = []int{1, 2, 3, 4, 5, 6, 7}
	fmt.Println(array_rot(arr, 2, 7))
}
