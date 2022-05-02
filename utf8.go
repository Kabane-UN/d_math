package main

import (
	"fmt"
	"math"
	"strconv"
)

func encode(utf32 []rune) []byte {
	var res []byte
	for i := 0; i < len(utf32); i++ {
		if utf32[i] < 128 {
			res = append(res, byte(utf32[i]))
		} else if utf32[i] < 2048 {
			g := utf32[i] % 64
			g2 := (utf32[i] - g) / 64
			res = append(res, byte(g2+192))
			res = append(res, byte(g+128))
			//fmt.Println(ConvertInt(strconv.Itoa(int(utf32[i])), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g)), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g2)), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g+128)), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g2+192)), 10, 2))
		} else if utf32[i] < 65536 {
			g := utf32[i] % 64
			g2 := (utf32[i] - g) / 64 % 64
			g3 := (utf32[i] - g - 64*g2) / 4096
			res = append(res, byte(g3+224))
			res = append(res, byte(g2+128))
			res = append(res, byte(g+128))
			//fmt.Println(ConvertInt(strconv.Itoa(int(utf32[i])), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g)), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g2)), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g3)), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g+128)), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g2+128)), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g3+224)), 10, 2))
		} else {
			g := utf32[i] % 64
			g2 := (utf32[i] - g) / 64 % 64
			g3 := (utf32[i] - g - 64*g2) / 4096 % 64
			g4 := (utf32[i] - g - 64*g2 - 4096*g3) / 262144
			res = append(res, byte(g4+240))
			res = append(res, byte(g3+128))
			res = append(res, byte(g2+128))
			res = append(res, byte(g+128))
			//fmt.Println(ConvertInt(strconv.Itoa(int(utf32[i])), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g)), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g2)), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g3)), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g4)), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g+128)), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g2+128)), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g3+128)), 10, 2))
			//fmt.Println(ConvertInt(strconv.Itoa(int(g4+240)), 10, 2))
		}
	}
	return res
}
func decode(utf8 []byte) []rune {
	var res []rune
	var buf rune
	bufcup := 0
	for i := 0; i < len(utf8); i++ {
		if utf8[i]/128 == 0 {
			res = append(res, rune(utf8[i]))
		} else if utf8[i]/64 == 2 {
			buf += rune(utf8[i]%64) * rune(int(math.Pow(64, float64(bufcup-1))))
			bufcup--
			if bufcup == 0 {
				res = append(res, rune(buf))
				buf = 0
			}
		} else if utf8[i]/32 == 6 {
			bufcup = 1
			buf = rune(utf8[i]%32) * 64
		} else if utf8[i]/16 == 14 {
			bufcup = 2
			buf = rune(utf8[i]%16) * 4096
		} else {
			bufcup = 3
			buf = rune(utf8[i]%8) * 262144
		}
	}
	return res
}
func main() {
	s := []rune("何か")
	fmt.Println(s)
	es := encode(s)
	fmt.Println(es)
	ds := decode(es)
	fmt.Println(ds)
	fmt.Println(string(ds))
}

func ConvertInt(val string, base, toBase int) (string, error) {
	i, err := strconv.ParseInt(val, base, 64)
	if err != nil {
		return "", err
	}
	return strconv.FormatInt(i, toBase), nil
}
