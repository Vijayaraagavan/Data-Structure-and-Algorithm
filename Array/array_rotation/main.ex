array_rot = fn (arr, d, n)
  -> Enum.slice(arr, d, n-d) ++ Enum.slice(arr,0,d)
end

l = [1,2,3,4,5,6,7]
res = array_rot.(l,3,7)
IO.puts("list is #{inspect(res)}")
