--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5 (Ubuntu 11.5-1.pgdg16.04+1)
-- Dumped by pg_dump version 11.5 (Ubuntu 11.5-1.pgdg16.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: ref_territory; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ref_territory (id, name) FROM stdin;
2	Kyivska
6	ternopilska
7	Kharkivska
3	Zhitomyrska
\.


--
-- Data for Name: ref_type_city; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_type_city (id, name) FROM stdin;
1	City
2	Town
3	Village
\.


--
-- Data for Name: ref_city; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_city (name, ter_id, id, type_city_id) FROM stdin;
Vyshneve	2	1	3
Kyiv	3	4	1
ternopil	6	6	1
ovruch	3	5	1
Kharkiv	7	7	1
zhitomir	3	2	1
\.


--
-- Data for Name: ref_market_view; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_market_view (id, name) FROM stdin;
1	retail
2	wholesale
3	others
\.


--
-- Data for Name: ref_price; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_price (id, name) FROM stdin;
1	Wholesale
2	Retail
\.


--
-- Data for Name: ref_type_contragent; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_type_contragent (id, name) FROM stdin;
1	buyer
4	seller
\.


--
-- Data for Name: ref_contragent; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_contragent (type_contra_id, city_id, id, price_id, market_view_id, name, address, contact, phone, comments) FROM stdin;
1	6	3	2	1	ATB	Ukraine	Direktor	067-123-45-66	Fucking olgarhs
\.


--
-- Data for Name: ref_type_doc; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_type_doc (id, name) FROM stdin;
\.


--
-- Data for Name: ref_kind_of_type_doc; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_kind_of_type_doc (id, type_doc_id, name) FROM stdin;
\.


--
-- Data for Name: ref_storage; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_storage (id, name, attribute) FROM stdin;
\.


--
-- Data for Name: doc_docs_head; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.doc_docs_head (id, contra_id, storage_in_id, storage_out_id, number, kind_of_type_doc_id, date) FROM stdin;
\.


--
-- Data for Name: doc_inventory; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.doc_inventory (id, storage_id, date) FROM stdin;
\.


--
-- Data for Name: doc_pko; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.doc_pko (id, contra_id, doc_id, sum, date) FROM stdin;
\.


--
-- Data for Name: doc_rko; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.doc_rko (id, contra_id, doc_id, sum, date) FROM stdin;
\.


--
-- Data for Name: ref_classification; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_classification (id, parent_id, name) FROM stdin;
\.


--
-- Data for Name: ref_type_lots; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_type_lots (id, name) FROM stdin;
\.


--
-- Data for Name: ref_kind_lots; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_kind_lots (id, name, type_lots_id) FROM stdin;
\.


--
-- Data for Name: ref_lots; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_lots (id, kind_lots_id, start_count, start_weight, start_age, number) FROM stdin;
\.


--
-- Data for Name: ref_size; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_size (id, name) FROM stdin;
\.


--
-- Data for Name: ref_nomenkl; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_nomenkl (id, classification_id, size_id, name) FROM stdin;
\.


--
-- Data for Name: journal_care_operations; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.journal_care_operations (id, lots_id, nomenkl_id, qty, sum, rec_time) FROM stdin;
\.


--
-- Data for Name: journal_operations_lots; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.journal_operations_lots (id, doc_id, kind_type_doc_id, storage_id, lots_id, rec_time, qty, sum, weight) FROM stdin;
\.


--
-- Data for Name: journal_operations_staff; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.journal_operations_staff (id, doc_id, kind_type_doc_id, storage_id, nomenkl_id, rec_time, qty, sum) FROM stdin;
\.


--
-- Data for Name: ref_roles; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_roles (id, name) FROM stdin;
\.


--
-- Data for Name: ref_employee; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_employee (id, roles_id, name, phone) FROM stdin;
\.


--
-- Data for Name: ref_payments_type; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_payments_type (id, name) FROM stdin;
\.


--
-- Data for Name: journal_payments; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.journal_payments (id, employee_id, payments_type_id, sum, date) FROM stdin;
\.


--
-- Data for Name: journal_working_time; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.journal_working_time (id, employee_id, plan_time, fact_time, date) FROM stdin;
\.


--
-- Data for Name: ref_living; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_living (id, employee_id, city_id, street, home, appartment, status) FROM stdin;
\.


--
-- Data for Name: ref_passport; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_passport (id, seria, number, output, date, employee_id, status) FROM stdin;
\.


--
-- Data for Name: ref_pay_rates; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_pay_rates (id, roles_id, payments_type_id, sum) FROM stdin;
\.


--
-- Data for Name: table_current_price; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.table_current_price (id, price_id, lots_id, cost) FROM stdin;
\.


--
-- Data for Name: table_current_rest_lots; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.table_current_rest_lots (id, storage_id, lots_id, qty, sum, plan_weight) FROM stdin;
\.


--
-- Data for Name: table_current_rest_stuff; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.table_current_rest_stuff (id, storage_id, nomenkl_id, qty, sum) FROM stdin;
\.


--
-- Data for Name: table_doc_lots; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.table_doc_lots (id, doc_id, lots_id, qty, sum, weight) FROM stdin;
\.


--
-- Data for Name: table_docs_stuff; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.table_docs_stuff (id, doc_id, nomenkl_id, qty, sum) FROM stdin;
\.


--
-- Data for Name: table_history_price; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.table_history_price (id, price_id, lots_id, date, cost) FROM stdin;
\.


--
-- Data for Name: table_history_rest_lots; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.table_history_rest_lots (id, storage_id, lots_id, qty, sum, plan_weight, fact_weight, date) FROM stdin;
\.


--
-- Data for Name: table_history_rest_stuff; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.table_history_rest_stuff (id, storage_id, nomenkl_id, date, qty, sum) FROM stdin;
\.


--
-- Data for Name: table_inventory_lots; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.table_inventory_lots (id, inventory_doc_id, lots_id, qty_sys, qty_fact) FROM stdin;
\.


--
-- Data for Name: table_inventory_stuff; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.table_inventory_stuff (id, inventory_doc_id, nomenkl_id, qty_sys, qty_fact) FROM stdin;
\.


--
-- Name: doc_docs_head_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.doc_docs_head_id_seq', 1, false);


--
-- Name: doc_inventory_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.doc_inventory_id_seq', 1, false);


--
-- Name: doc_pko_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.doc_pko_id_seq', 1, false);


--
-- Name: doc_rko_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.doc_rko_id_seq', 1, false);


--
-- Name: journal_care_operations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.journal_care_operations_id_seq', 1, false);


--
-- Name: journal_operations_lots_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.journal_operations_lots_id_seq', 1, false);


--
-- Name: journal_payments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.journal_payments_id_seq', 1, false);


--
-- Name: journal_working_time_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.journal_working_time_id_seq', 1, false);


--
-- Name: ref_city_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_city_id_seq', 7, true);


--
-- Name: ref_classification_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_classification_id_seq', 1, false);


--
-- Name: ref_contragent_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_contragent_id_seq', 3, true);


--
-- Name: ref_employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_employee_id_seq', 1, false);


--
-- Name: ref_kind_lots_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_kind_lots_id_seq', 1, false);


--
-- Name: ref_kind_of_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_kind_of_type_id_seq', 1, false);


--
-- Name: ref_living_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_living_id_seq', 1, false);


--
-- Name: ref_lots_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_lots_id_seq', 1, false);


--
-- Name: ref_market_view_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_market_view_id_seq', 3, true);


--
-- Name: ref_nomenkl_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_nomenkl_id_seq', 1, false);


--
-- Name: ref_passport_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_passport_id_seq', 1, false);


--
-- Name: ref_pay_rates_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_pay_rates_id_seq', 1, false);


--
-- Name: ref_payments_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_payments_type_id_seq', 1, false);


--
-- Name: ref_price_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_price_id_seq', 2, true);


--
-- Name: ref_roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_roles_id_seq', 1, false);


--
-- Name: ref_size_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_size_id_seq', 1, false);


--
-- Name: ref_storage_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_storage_id_seq', 1, false);


--
-- Name: ref_territory_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ref_territory_id_seq', 7, true);


--
-- Name: ref_type_city_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_type_city_id_seq', 3, true);


--
-- Name: ref_type_contragent_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_type_contragent_id_seq', 4, true);


--
-- Name: ref_type_doc_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_type_doc_id_seq', 1, false);


--
-- Name: ref_type_lots_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.ref_type_lots_id_seq', 1, false);


--
-- Name: table_current_price_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.table_current_price_id_seq', 1, false);


--
-- Name: table_current_rest_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.table_current_rest_id_seq', 1, false);


--
-- Name: table_current_rest_lots_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.table_current_rest_lots_id_seq', 1, false);


--
-- Name: table_doc_lots_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.table_doc_lots_id_seq', 1, false);


--
-- Name: table_docs_stuff_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.table_docs_stuff_id_seq', 1, false);


--
-- Name: table_history_rest_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.table_history_rest_id_seq', 1, false);


--
-- Name: table_history_rest_lots_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.table_history_rest_lots_id_seq', 1, false);


--
-- Name: table_inventory_lots_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.table_inventory_lots_id_seq', 1, false);


--
-- Name: table_inventory_stuff_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.table_inventory_stuff_id_seq', 1, false);


--
-- Name: table_operations_staff_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.table_operations_staff_id_seq', 1, false);


--
-- Name: table_price_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pete
--

SELECT pg_catalog.setval('public.table_price_id_seq', 1, false);


--
-- PostgreSQL database dump complete
--

