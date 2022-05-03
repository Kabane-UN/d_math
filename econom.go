package main

import (
	"bufio"
	"container/list"
	"fmt"
	"os"
	"strings"
	"unicode"
)

func i_hate_parsing(s []string, index, countb int) (*list.List, int, int) {
	parsed := list.New()
	var c *list.List
	if index >= len(s) {
		return nil, index, countb
	}
	if s[index] == "(" {
		k := countb
		countb++
		index++
		c, index, countb = i_hate_parsing(s, index, countb)
		parsed.PushBackList(c)
		for countb != k {
			c, index, countb = i_hate_parsing(s, index, countb)

			if c != nil {
				if c.Front().Value == "$" || c.Front().Value == "@" || c.Front().Value == "#" {
					parsed.PushBack(c)
				} else {
					parsed.PushBackList(c)
				}
			}
		}
		return parsed, index, countb
	} else if s[index] != ")" {
		parsed.PushBack(s[index])
		index++
		c, index, countb = i_hate_parsing(s, index, countb)
		if c != nil {
			if c.Front().Value == "$" || c.Front().Value == "#" || c.Front().Value == "@" {
				parsed.PushBack(c)
			} else {
				parsed.PushBackList(c)
			}
		} else {
			return parsed, index, countb
		}
	} else {
		countb--
		index++
		return nil, index, countb
	}
	return parsed, index, countb
}
func cal(tree *list.List) (string, []string) {
	var set []string
	var extr string
	if unicode.IsLetter(rune(tree.Front().Value.(string)[0])) {
		extr, _ = tree.Front().Value.(string)
		return extr, nil
	} else {
		p := tree.Front().Next()
		extr += tree.Front().Value.(string)
		for p != nil {
			switch p.Value.(type) {
			case *list.List:
				c, s := cal(p.Value.(*list.List))
				extr += c
				set = append(set, s...)
			default:
				c := p.Value.(string)
				extr += c
			}
			p = p.Next()
		}
		set = append(set, extr)
	}
	return extr, set
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
	parsed, _, _ := i_hate_parsing(newarline, 0, 0)
	_, nedoset := cal(parsed)
	var normset = make(map[string]struct{})
	for _, s := range nedoset {
		normset[s] = struct{}{}
	}
	fmt.Println(len(normset))
}
