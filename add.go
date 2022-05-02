package main

import (
	"fmt"
)

func add(a, b []int32, p int) []int32 {
	if len(a) < len(b) {
		a, b = b, a
	}
	var buf int32 = 0
	var res []int32
	for i := 0; i < len(a); i++ {
		g := int32(0)
		if len(b) > i {
			g = a[i] + b[i] + buf
		} else {
			g = a[i] + buf
		}
		buf = g / int32(p)
		res = append(res, g%int32(p))
	}
	if buf != 0 {
		res = append(res, buf)
	}
	return res
}
func main() {
	a := []int32{2, 4}
	b := []int32{4}
	fmt.Println(add(a, b, 10))
}
