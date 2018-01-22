fun is_order (p1: int * int * int, p2: int * int * int) =
  if (#1 p1 < #1 p2) andalso (#2 p1 < #2 p2) andalso (#3 p1 < #3 p2)
  then true
  else false



fun number_in_month (a: (int * int * int) list,b:int) =
    if null a
    then 0
    else
        let
            fun sum(l: int list) =
              if null l
              then 0
              else (hd l) + sum(tl l)
        in
            let
                fun count(from: (int * int * int) list) =
                  if null (tl from)
                  then
                      if #2 (hd from) == b
                      then 1::[]
                      else 0::[]
                  else
                      let val hdl = hd from
                      in
                          if #2 hdl == b
                          then 1::count(tl from)
                          else 0::count(tl from)
                      end
            in
                sum(count(a))
            end
        end


fun number_in_months (l: (int * int * int) list, m: int list) =
    if null l
    then 0
    else if null m
    then 0
    else
        let fun sum(c: int list) =
              if null c
              then 0
              else (hd c) + sum(tl c)
        in
            let fun count_sum (mm: int list) =
                if null (tl mm)
                then number_in_month(l, hd mm)::[]
                else number_in_month(l, hd mm)::count_sum(tl mm)
            in
                sum(count_sum(m))
            end
        end


fun dates_in_month (l: (int * int * int) list, m: int) =
  if null l
  then []
  else if m<=0 andalso m>12
  then []
  else if #2 (hd l) == m
  then (hd l)::[]
  else dates_in_month(tl l, m)


 fun dates_in_months (l: (int * int * int) list, m: int list) =
     if null l
     then []
     else if null m
     then []
     else
         let fun count_month(ms: int list) =
             if null ms
             then []
             else dates_in_month(hd ms)::count_month(tl ms)
         in
             count_month(m)
         end


fun get_nth (l: string list, ind: int) =
    if null l
    then ""
    else if ind < 1
    then ""
    else if ind == 1
    then hd l
    else get_nth(tl l,ind -1)


fun date_to_string (y: int, m: int, d:int) =
  if (m<=0 andalso m>12)
  then "Error Months"
  else
      let
          val months = ["Jan","Feb","March","May","June","July","Agu","Sept","Obt","Nov","Dec"]
      in
            let
                val month = get_nth(months,m)
            in
                month + Int.toString(d) + "," + Int.toString(y)
            end
       end



fun number_before_reaching_sum (ind: int, l: int list) =
  if ind == 0
  then 0
  else if null l
  then 0
  else if ind <= hd l
  then 0
  else
      let fun search_sum (x: int, li: int list) =
            if null li
            then 0
            else if x - (hd li) <= hd li
            then hd li
            else search_sum(x - (hd li), tl li)
      in
          search_sum(ind, l)
      end


fun what_month (a: int) =
  if a < 1 andalso a > 365
  then 0
  else
      let
          val m = [(1,31),(2,28),(3,31),(4,30),(5,31),(6,30),(7,31),(8,31),(9,30),(10,31),(11,30),(12,31)]
      in
          let
          fun find_month (l: (int * int) list, d: int) =
            if null l
            then 0
            else if d <= 0
            then 0
            else
                if d - (#2 (hd l)) <= (#2 (hd l))
                then #1 (hd l)
                else find_month(tl l, d - (#2 (hd l)))
          in
              find_month(m, a)
          end
       end


fun month_range (a: int, b: int) =
  if(a < b)
  then [0]
  else
      let fun m_range(x: int) =
          if (x == b)
          then what_month(x)::[]
          else what_month(x)::m_range(x+1)
      in
          m_range(a)
      end


fun oldest (l: (int * int * int) list) =
  if null l
  then NONE
  else if null (tl l)
  then SOME(hd l)
  else
      let
          fun compare_oldest(x: (int * int * int), y: (int * int * int) list) =
            if null y
            then SOME(x)
            else if is_order(x, hd y)
            then compare_oldest(x, tl y)
            else compare_oldest(hd y, tl y)
      in
          compare_oldest(hd l, tl l)
      end
