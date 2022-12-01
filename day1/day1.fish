awk -f day1.awk input.txt | sort -g | tail -3 | awk '{S+=$0} END {print S}'
