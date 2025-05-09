alter table borrow_records
    add due_date date default (curdate()) not null after borrow_date;

alter table borrow_records
    modify return_date date default (curdate()) null;

