INSERT INTO public.categories (id, name, type, created_at, user_id) VALUES (1, 'Income', 'INCOME', '2017-02-15 10:01:16.113000', 1);
INSERT INTO public.categories (id, name, type, created_at, user_id) VALUES (2, 'Home', 'EXPENDITURE', '2017-02-15 10:01:16.113000', 1);
INSERT INTO public.categories (id, name, type, created_at, user_id) VALUES (3, 'Transportation', 'EXPENDITURE', '2017-02-15 10:01:16.113000', 1);
INSERT INTO public.categories (id, name, type, created_at, user_id) VALUES (4, 'Health', 'EXPENDITURE', '2017-02-15 10:01:16.113000', 1);
INSERT INTO public.categories (id, name, type, created_at, user_id) VALUES (5, 'Charity/Gifts', 'EXPENDITURE', '2017-02-15 10:01:16.113000', 1);
INSERT INTO public.categories (id, name, type, created_at, user_id) VALUES (6, 'Daily Living', 'EXPENDITURE', '2017-02-15 10:01:16.113000', 1);
INSERT INTO public.categories (id, name, type, created_at, user_id) VALUES (7, 'Entertainment', 'EXPENDITURE', '2017-02-15 10:01:16.113000', 1);
INSERT INTO public.categories (id, name, type, created_at, user_id) VALUES (8, 'Savings', 'EXPENDITURE', '2017-02-15 10:01:16.113000', 1);
INSERT INTO public.categories (id, name, type, created_at, user_id) VALUES (9, 'Obligations', 'EXPENDITURE', '2017-02-15 10:01:16.113000', 1);
INSERT INTO public.categories (id, name, type, created_at, user_id) VALUES (10, 'Miscellaneous', 'EXPENDITURE', '2017-02-15 10:01:16.113000', 1);

INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (1, 'Wages & Tips', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 1, 1);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (2, 'Interest Income', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 1, 2);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (3, 'Dividends', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 1, 3);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (4, 'Gifts Received', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 1, 4);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (5, 'Refunds/Reimbursements', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 1, 5);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (6, 'Transfer From Savings', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 1, 6);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (7, 'Mortgage/Rent', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 2, 7);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (8, 'Home/Rental Insurance', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 2, 8);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (9, 'Electricity', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 2, 9);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (10, 'Gas/Oil', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 2, 10);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (11, 'Water/Sewer/Trash', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 2, 11);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (12, 'Phone', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 2, 12);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (13, 'Cable/Satellite', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 2, 13);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (14, 'Internet', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 2, 14);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (15, 'Furnishings/Appliances', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 2, 15);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (16, 'Maintenance/Supplies', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 2, 16);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (17, 'Vehicle Payments', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 3, 17);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (18, 'Auto Insurance', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 3, 18);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (19, 'Fuel', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 3, 19);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (20, 'Bus/Taxi/Train Fare', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 3, 20);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (21, 'Repairs', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 3, 21);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (22, 'Registration/License', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 3, 22);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (23, 'Health Insurance', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 4, 23);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (24, 'Doctor/Dentist', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 4, 24);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (25, 'Medicine/Drugs', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 4, 25);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (26, 'Health Club Dues', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 4, 26);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (27, 'Life Insurance', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 4, 27);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (28, 'Veterinarian/Pet Care', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 4, 28);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (29, 'Gifts Given', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 5, 29);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (30, 'Charitable Donations', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 5, 30);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (31, 'Religious Donations', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 5, 31);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (32, 'Groceries', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 6, 32);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (33, 'Personal Supplies', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 6, 33);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (34, 'Clothing', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 6, 34);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (35, 'Cleaning', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 6, 35);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (36, 'Education/Lessons', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 6, 36);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (37, 'Dining/Eating Out', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 6, 37);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (38, 'Salon/Barber', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 6, 38);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (39, 'Pet Food', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 6, 39);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (40, 'Videos/DVDs', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 7, 40);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (41, 'Music', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 7, 41);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (42, 'Games', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 7, 42);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (43, 'Rentals', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 7, 43);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (44, 'Movies/Theater', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 7, 44);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (45, 'Concerts/Plays', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 7, 45);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (46, 'Books', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 7, 46);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (47, 'Hobbies', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 7, 47);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (48, 'Film/Photos', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 7, 48);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (49, 'Sports', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 7, 49);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (50, 'Outdoor Recreation', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 7, 50);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (51, 'Toys/Gadgets', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 7, 51);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (52, 'Vacation/Travel', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 7, 52);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (53, 'Emergency Fund', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 8, 53);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (54, 'Transfer to Savings', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 8, 54);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (55, 'Retirement (401k, IRA)', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 8, 55);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (56, 'Investments', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 8, 56);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (57, 'Education', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 8, 57);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (58, 'Student Loan', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 9, 58);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (59, 'Other Loan', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 9, 59);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (60, 'Credit Cards', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 9, 60);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (61, 'Alimony/Child Care', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 9, 61);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (62, 'Federal Taxes', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 9, 62);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (63, 'State/Local Taxes', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 9, 63);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (64, 'Bank Fees', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 10, 64);
INSERT INTO public.budgets (id, name, projected, actual, period_on, created_at, user_id, category_id) VALUES (65, 'Postage', 0.00, 0.00, '2017-02-01', '2017-02-15 10:01:16.113000', 1, 10, 65);


INSERT INTO public.users (id, username, password, name, created_at, currency) VALUES (1, 'frienheint89@yandex.ru', '6a4e7bca36d4f95feb5ba517abbe42898b14781fc3ea30af4276287ab87348fcd1948b83760b507b', null, '2017-02-15 10:01:16.113000', null);

