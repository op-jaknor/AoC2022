import Data.Char(ord, isUpper)

main = do
  list <- readFile "./input.txt"
  print (foldl (\acc num -> acc + num) 0 (map solve (lines list)))

solve str = convertLetterToNumber (findDuplicatedLetter str)

convertLetterToNumber (letter:list) = if (isUpper letter) then (ord letter) - 38 else (ord letter) - 96

findDuplicatedLetter string =
  let (xs, ys) = splitListInHalf string
  in [x | x <- xs, y <- ys, x == y]

halfPointIndex list = ((length list) `div` 2)

splitListInHalf list = (splitAt (halfPointIndex list) list)
