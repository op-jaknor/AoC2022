awk '{
if($0>0)
CURR+=$0
else
{print CURR
CURR = 0}}'
 input.txt | sort -g | tail -3 | awk '{S+=$0} END {print S}'







