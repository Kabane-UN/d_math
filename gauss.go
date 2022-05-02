package main

import (
	"fmt"
	"os"
	"strconv"
)

func main() {
	var n int
	fmt.Scan(&n)
	var matrix [][][]int
	for i := 0; i < n; i++ {
		var g [][]int
		for j := 0; j < n+1; j++ {
			var buf int
			fmt.Scan(&buf)
			g2 := []int{buf, 1}
			g = append(g, g2)
		}
		matrix = append(matrix, g)
	}
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			c1 := matrix[i][i][1] * matrix[j][i][0]
			c2 := matrix[i][i][0] * matrix[j][i][1]
			for k := 0; k < n+1; k++ {
				matrix[j][k][0] = matrix[j][k][0]*matrix[i][k][1]*c2 - matrix[i][k][0]*c1*matrix[j][k][1]
				matrix[j][k][1] *= matrix[i][k][1] * c2
				matrix[j][k] = normalize(matrix[j][k])

			}
		}
	}
	var resvec [][]int
	for i := 0; i < n; i++ {
		resvec = append(resvec, nil)
	}
	for i := n - 1; i > -1; i-- {
		var g []int
		g = append(g, 0)
		g = append(g, 1)
		for j := n - 1; j >= i; j-- {
			if j == i {
				var g2 []int
				g2 = append(g2, matrix[i][n][0]*g[1]-g[0]*matrix[i][n][1])
				g2 = append(g2, g[1]*matrix[i][n][1])
				g2 = normalize(g2)
				g[0] = g2[0] * matrix[i][j][1]
				g[1] = g2[1] * matrix[i][j][0]
				g = normalize(g)
			} else {
				var g2 []int
				g2 = append(g2, resvec[j][0]*matrix[i][j][0])
				g2 = append(g2, resvec[j][1]*matrix[i][j][1])
				g2 = normalize(g2)
				g[0] = g[0]*g2[1] + g2[0]*g[1]
				g[1] *= g2[1]
				g = normalize(g)
			}
		}
		resvec[i] = g
	}

	for _, i := range resvec {
		c := normalize(i)
		fmt.Println(strconv.Itoa(c[0]) + "/" + strconv.Itoa(c[1]))
	}
}

func gcd(x, y int) int {
	for y != 0 {
		x, y = y, x%y
	}
	return x
}
func normalize(a []int) []int {
	norm := gcd(a[0], a[1])
	var res []int
	if norm == 0 {
		fmt.Println("No solution")
		os.Exit(0)
	}
	res = append(res, a[0]/norm)
	res = append(res, a[1]/norm)
	return res
}
