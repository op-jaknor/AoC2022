use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;
use regex::Regex;

fn main() {
    if let Ok(contents) = read_lines("./input.txt") {
        let mut counter = 0;
        for line in contents {
            if let Ok(valid_line) = line {
                let exp = Regex::new(r"(-|,)").unwrap();
                let mut chars = exp.split(&valid_line);
                let l1 = chars.next();
                let u1 = chars.next();
                let l2 = chars.next();
                let u2 = chars.next();
                if l1.unwrap().parse::<i32>().unwrap() >= l2.unwrap().parse::<i32>().unwrap() && l1.unwrap().parse::<i32>().unwrap() <= u2.unwrap().parse::<i32>().unwrap()  {
                    counter += 1;
                } else if l2.unwrap().parse::<i32>().unwrap() >= l1.unwrap().parse::<i32>().unwrap() && l2.unwrap().parse::<i32>().unwrap() <= u1.unwrap().parse::<i32>().unwrap() {
                    counter += 1;
                }
            }
        }
        println!("{}", counter)
    } else {
        println!("Couldn't read file (maybe)")
    }

}

fn read_lines<P>(filename: P) -> io::Result<io::Lines<io::BufReader<File>>>
where P: AsRef<Path>, {
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
}
