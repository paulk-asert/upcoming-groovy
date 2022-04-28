from p in persons
leftjoin c in cities on p.city.name == c.name
where c.name == 'Shanghai'
select p.name, c.name as cityName

from p in persons
groupby p.gender
having p.gender == 'Male'
select p.gender, max(p.age)

from p in persons
orderby p.age in desc, p.name
select p.name

from n in numbers
where n > 0 && n <= 3
select n * 2

from n1 in nums1
innerjoin n2 in nums2 on n1 == n2
select n1 + 1, n2
