DELIMITER //
CREATE PROCEDURE abc(In flag bit)
BEGIN
    if flag = true then
select * from product order by price asc;
end if;
    if flag = false then
select * from product order by price desc;
end if;
end; //
DELIMITER ;
CALL abc(true);

select p.id, p.name, sum(od.quantity)
from product p
         join order_detail od on p.id = od.product_id
         join invoice.orderr o on o.id = od.orderr_id
where o.create_at between '2022-06-22' and '2022-06-23'
group by p.id;
