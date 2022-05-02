package main

import (
	"bufio"
	"container/list"
	"fmt"
	"os"
	"strconv"
	"strings"
	"unicode"
)

func i_hate_parsing(s []string, index int) (*list.List, int) {
	parsed := list.New()
	var c *list.List
	if s[index] == "(" {
		index++
		c, index = i_hate_parsing(s, index)
		parsed.PushBackList(c)
		return parsed, index
	} else if s[index] != ")" {
		parsed.PushBack(s[index])
		index++
		c, index = i_hate_parsing(s, index)
		if c != nil {
			if c.Front().Value == "*" || c.Front().Value == "+" || c.Front().Value == "-" {
				parsed.PushBack(c)
			} else {
				parsed.PushBackList(c)
			}
		} else {
			return parsed, index
		}
	} else {
		index++
		return nil, index
	}
	return parsed, index
}
func cal(tree *list.List) int {
	var res int
	var vals []int
	if unicode.IsDigit(rune(tree.Front().Value.(string)[0])) {
		res, _ = strconv.Atoi(tree.Front().Value.(string))
	} else {
		p := tree.Front().Next()
		for p != nil {
			switch p.Value.(type) {
			case *list.List:
				c := cal(p.Value.(*list.List))
				vals = append(vals, c)
			default:
				c, _ := strconv.Atoi(p.Value.(string))
				vals = append(vals, c)
			}
			p = p.Next()
		}
		if tree.Front().Value == "*" {
			res = 1
			for _, i := range vals {
				res *= i
			}
		} else if tree.Front().Value == "+" {
			res = 0
			for _, i := range vals {
				res += i
			}
		} else {
			res = 0
			for _, i := range vals {
				res -= i
			}
		}
	}
	return res
}
func main() {
	myscanner := bufio.NewScanner(os.Stdin)
	myscanner.Scan()
	line := myscanner.Text()
	arline := strings.Split(line, "")
	var newarline []string
	for i := 0; i < len(arline); i++ {
		if arline[i] != " " {
			newarline = append(newarline, arline[i])
		}
	}
	parsed, _ := i_hate_parsing(newarline, 0)
	fmt.Println(cal(parsed))
}
