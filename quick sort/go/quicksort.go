package main

import "fmt"

func Swap(a []int, l, r int) []int {
	temp := a[l]
	a[l] =  a[r]
	a[r] = temp
	return a
}
func Partition(a []int, low, high int) int{
	track := low-1
	pivot := a[high]

	for k, i := range a {
		if i < pivot {
			track++
			a = Swap(a, track, k)
		}
	}
	a = Swap(a, track+1, high)
	return track
}

func Quicksort() {

}
func main() {
	arr := make([]int, 6)
	f := &arr
	fmt.Printf("%T", f)
	// arr = []int{10,80,30,90,40,50,70}
	arr = []int{1,2,3,4,5,6,7}
	track := Partition(arr, 0, 6)
	fmt.Println(track, arr)
}

/*
	This is sorting quiet confusing but lets do it
	10 80 30 90 40 50 70

	It doesn't matter if a num is greater than or less than its swapping counter part
	it is swapped
	the condition is it should be less than pivot

	j = 0:
		i = -1 => 10 < 70 => so i++ => i=0
		swap arr[i] and arr[j] => swap (10, 10)
	j = 1:
		i = 0 => 80 > 70 => continue
	j = 2:
		i = 0 => 30 < 70 => i++ => i = 1
		swap arr[i] and arr[j] => swap (80, 30)


input: 	1 2 3 4 5 6 7 

		j = 0 i = 0 	1 2 3 4 5 6 7
		j = 1 i = 1 	1 2 3 4 5 6 7
		j = 2 i = 2 	1 2 3 4 5 6 7
		j = 3 i = 3 	1 2 3 4 5 6 7
		j = 4 i = 4 	1 2 3 4 5 6 7
		j = 5 i = 5 	1 2 3 4 5 6 7
		j = 6 i = 6 	1 2 3 4 5 6 7
		out of loop of j : atlast swap arr[i+1] and arr[high] i.e 7 itself
*/
