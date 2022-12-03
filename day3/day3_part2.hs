import Data.Char(ord, isUpper)

main = do
  list <- readFile "./input.txt"
  print (foldl (\acc num -> acc + num) 0 (solve2 (lines list)))

solve2 [] = []
solve2 (l1:l2:l3:ls) = (convertLetterToNumber (findTriplicatedLetter l1 l2 l3)):(solve2 ls)

convertLetterToNumber (letter:list) = if (isUpper letter) then (ord letter) - 38 else (ord letter) - 96

findTriplicatedLetter l1 l2 l3 = [x | x <- l1, y <- l2, z <- l3, x == y && x == z]
