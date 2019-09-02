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
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: doc_docs_head; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.doc_docs_head (
    id bigint NOT NULL,
    contra_id bigint,
    storage_in_id bigint,
    storage_out_id bigint,
    number integer,
    kind_of_type_doc_id bigint,
    date date
);


ALTER TABLE public.doc_docs_head OWNER TO pete;

--
-- Name: doc_docs_head_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.doc_docs_head_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.doc_docs_head_id_seq OWNER TO pete;

--
-- Name: doc_docs_head_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.doc_docs_head_id_seq OWNED BY public.doc_docs_head.id;


--
-- Name: doc_inventory; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.doc_inventory (
    id bigint NOT NULL,
    storage_id bigint NOT NULL,
    date date
);


ALTER TABLE public.doc_inventory OWNER TO pete;

--
-- Name: doc_inventory_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.doc_inventory_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.doc_inventory_id_seq OWNER TO pete;

--
-- Name: doc_inventory_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.doc_inventory_id_seq OWNED BY public.doc_inventory.id;


--
-- Name: doc_pko; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.doc_pko (
    id bigint NOT NULL,
    contra_id bigint NOT NULL,
    doc_id bigint NOT NULL,
    sum real,
    date date
);


ALTER TABLE public.doc_pko OWNER TO pete;

--
-- Name: doc_pko_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.doc_pko_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.doc_pko_id_seq OWNER TO pete;

--
-- Name: doc_pko_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.doc_pko_id_seq OWNED BY public.doc_pko.id;


--
-- Name: doc_rko; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.doc_rko (
    id bigint NOT NULL,
    contra_id bigint NOT NULL,
    doc_id bigint NOT NULL,
    sum real,
    date date
);


ALTER TABLE public.doc_rko OWNER TO pete;

--
-- Name: doc_rko_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.doc_rko_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.doc_rko_id_seq OWNER TO pete;

--
-- Name: doc_rko_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.doc_rko_id_seq OWNED BY public.doc_rko.id;


--
-- Name: journal_care_operations; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.journal_care_operations (
    id bigint NOT NULL,
    lots_id bigint NOT NULL,
    nomenkl_id bigint NOT NULL,
    qty integer,
    sum real,
    rec_time date
);


ALTER TABLE public.journal_care_operations OWNER TO pete;

--
-- Name: journal_care_operations_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.journal_care_operations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.journal_care_operations_id_seq OWNER TO pete;

--
-- Name: journal_care_operations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.journal_care_operations_id_seq OWNED BY public.journal_care_operations.id;


--
-- Name: journal_operations_lots; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.journal_operations_lots (
    id bigint NOT NULL,
    doc_id bigint NOT NULL,
    kind_type_doc_id bigint NOT NULL,
    storage_id bigint NOT NULL,
    lots_id bigint NOT NULL,
    rec_time date,
    qty integer,
    sum real,
    weight real
);


ALTER TABLE public.journal_operations_lots OWNER TO pete;

--
-- Name: journal_operations_lots_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.journal_operations_lots_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.journal_operations_lots_id_seq OWNER TO pete;

--
-- Name: journal_operations_lots_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.journal_operations_lots_id_seq OWNED BY public.journal_operations_lots.id;


--
-- Name: journal_operations_staff; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.journal_operations_staff (
    id bigint NOT NULL,
    doc_id bigint NOT NULL,
    kind_type_doc_id bigint NOT NULL,
    storage_id bigint NOT NULL,
    nomenkl_id bigint NOT NULL,
    rec_time date,
    qty integer,
    sum real
);


ALTER TABLE public.journal_operations_staff OWNER TO pete;

--
-- Name: journal_payments; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.journal_payments (
    id bigint NOT NULL,
    employee_id bigint NOT NULL,
    payments_type_id bigint NOT NULL,
    sum real,
    date date
);


ALTER TABLE public.journal_payments OWNER TO pete;

--
-- Name: journal_payments_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.journal_payments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.journal_payments_id_seq OWNER TO pete;

--
-- Name: journal_payments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.journal_payments_id_seq OWNED BY public.journal_payments.id;


--
-- Name: journal_working_time; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.journal_working_time (
    id bigint NOT NULL,
    employee_id bigint NOT NULL,
    plan_time integer,
    fact_time integer,
    date date
);


ALTER TABLE public.journal_working_time OWNER TO pete;

--
-- Name: journal_working_time_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.journal_working_time_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.journal_working_time_id_seq OWNER TO pete;

--
-- Name: journal_working_time_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.journal_working_time_id_seq OWNED BY public.journal_working_time.id;


--
-- Name: ref_city; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_city (
    name character varying(50),
    ter_id bigint NOT NULL,
    id bigint NOT NULL,
    type_city_id bigint
);


ALTER TABLE public.ref_city OWNER TO pete;

--
-- Name: ref_city_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_city_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_city_id_seq OWNER TO pete;

--
-- Name: ref_city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_city_id_seq OWNED BY public.ref_city.id;


--
-- Name: ref_classification; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_classification (
    id bigint NOT NULL,
    parent_id bigint,
    name character varying(50)
);


ALTER TABLE public.ref_classification OWNER TO pete;

--
-- Name: ref_classification_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_classification_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_classification_id_seq OWNER TO pete;

--
-- Name: ref_classification_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_classification_id_seq OWNED BY public.ref_classification.id;


--
-- Name: ref_contragent; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_contragent (
    type_contra_id bigint NOT NULL,
    city_id bigint NOT NULL,
    id bigint NOT NULL,
    price_id bigint,
    market_view_id bigint NOT NULL,
    name character varying(100),
    address character varying(100),
    contact character varying(50),
    phone character varying(20),
    comments character varying(255)
);


ALTER TABLE public.ref_contragent OWNER TO pete;

--
-- Name: ref_contragent_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_contragent_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_contragent_id_seq OWNER TO pete;

--
-- Name: ref_contragent_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_contragent_id_seq OWNED BY public.ref_contragent.id;


--
-- Name: ref_employee; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_employee (
    id bigint NOT NULL,
    roles_id bigint NOT NULL,
    name character varying(50),
    phone character varying(20)
);


ALTER TABLE public.ref_employee OWNER TO pete;

--
-- Name: ref_employee_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_employee_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_employee_id_seq OWNER TO pete;

--
-- Name: ref_employee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_employee_id_seq OWNED BY public.ref_employee.id;


--
-- Name: ref_kind_lots; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_kind_lots (
    id bigint NOT NULL,
    name character varying(20),
    type_lots_id bigint NOT NULL
);


ALTER TABLE public.ref_kind_lots OWNER TO pete;

--
-- Name: ref_kind_lots_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_kind_lots_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_kind_lots_id_seq OWNER TO pete;

--
-- Name: ref_kind_lots_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_kind_lots_id_seq OWNED BY public.ref_kind_lots.id;


--
-- Name: ref_kind_of_type_doc; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_kind_of_type_doc (
    id bigint NOT NULL,
    type_doc_id bigint NOT NULL,
    name character varying(20)
);


ALTER TABLE public.ref_kind_of_type_doc OWNER TO pete;

--
-- Name: ref_kind_of_type_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_kind_of_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_kind_of_type_id_seq OWNER TO pete;

--
-- Name: ref_kind_of_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_kind_of_type_id_seq OWNED BY public.ref_kind_of_type_doc.id;


--
-- Name: ref_living; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_living (
    id bigint NOT NULL,
    employee_id bigint NOT NULL,
    city_id bigint NOT NULL,
    street character varying(50),
    home character(10),
    appartment integer,
    status boolean NOT NULL
);


ALTER TABLE public.ref_living OWNER TO pete;

--
-- Name: ref_living_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_living_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_living_id_seq OWNER TO pete;

--
-- Name: ref_living_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_living_id_seq OWNED BY public.ref_living.id;


--
-- Name: ref_lots; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_lots (
    id bigint NOT NULL,
    kind_lots_id bigint NOT NULL,
    start_count integer NOT NULL,
    start_weight real NOT NULL,
    start_age integer NOT NULL,
    number integer
);


ALTER TABLE public.ref_lots OWNER TO pete;

--
-- Name: ref_lots_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_lots_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_lots_id_seq OWNER TO pete;

--
-- Name: ref_lots_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_lots_id_seq OWNED BY public.ref_lots.id;


--
-- Name: ref_market_view; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_market_view (
    id bigint NOT NULL,
    name character varying
);


ALTER TABLE public.ref_market_view OWNER TO pete;

--
-- Name: ref_market_view_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_market_view_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_market_view_id_seq OWNER TO pete;

--
-- Name: ref_market_view_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_market_view_id_seq OWNED BY public.ref_market_view.id;


--
-- Name: ref_nomenkl; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_nomenkl (
    id bigint NOT NULL,
    classification_id bigint NOT NULL,
    size_id bigint NOT NULL,
    name character varying(50)
);


ALTER TABLE public.ref_nomenkl OWNER TO pete;

--
-- Name: ref_nomenkl_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_nomenkl_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_nomenkl_id_seq OWNER TO pete;

--
-- Name: ref_nomenkl_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_nomenkl_id_seq OWNED BY public.ref_nomenkl.id;


--
-- Name: ref_passport; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_passport (
    id bigint NOT NULL,
    seria character varying(2) NOT NULL,
    number character varying(6),
    output character varying(100),
    date date,
    employee_id bigint,
    status boolean NOT NULL
);


ALTER TABLE public.ref_passport OWNER TO pete;

--
-- Name: ref_passport_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_passport_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_passport_id_seq OWNER TO pete;

--
-- Name: ref_passport_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_passport_id_seq OWNED BY public.ref_passport.id;


--
-- Name: ref_pay_rates; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_pay_rates (
    id bigint NOT NULL,
    roles_id bigint NOT NULL,
    payments_type_id bigint NOT NULL,
    sum real
);


ALTER TABLE public.ref_pay_rates OWNER TO pete;

--
-- Name: ref_pay_rates_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_pay_rates_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_pay_rates_id_seq OWNER TO pete;

--
-- Name: ref_pay_rates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_pay_rates_id_seq OWNED BY public.ref_pay_rates.id;


--
-- Name: ref_payments_type; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_payments_type (
    id bigint NOT NULL,
    name character varying(20)
);


ALTER TABLE public.ref_payments_type OWNER TO pete;

--
-- Name: ref_payments_type_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_payments_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_payments_type_id_seq OWNER TO pete;

--
-- Name: ref_payments_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_payments_type_id_seq OWNED BY public.ref_payments_type.id;


--
-- Name: ref_price; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_price (
    id bigint NOT NULL,
    name character varying(20)
);


ALTER TABLE public.ref_price OWNER TO pete;

--
-- Name: ref_price_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_price_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_price_id_seq OWNER TO pete;

--
-- Name: ref_price_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_price_id_seq OWNED BY public.ref_price.id;


--
-- Name: ref_roles; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_roles (
    id bigint NOT NULL,
    name character varying(20)
);


ALTER TABLE public.ref_roles OWNER TO pete;

--
-- Name: ref_roles_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_roles_id_seq OWNER TO pete;

--
-- Name: ref_roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_roles_id_seq OWNED BY public.ref_roles.id;


--
-- Name: ref_size; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_size (
    id bigint NOT NULL,
    name character varying(10)
);


ALTER TABLE public.ref_size OWNER TO pete;

--
-- Name: ref_size_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_size_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_size_id_seq OWNER TO pete;

--
-- Name: ref_size_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_size_id_seq OWNED BY public.ref_size.id;


--
-- Name: ref_storage; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_storage (
    id bigint NOT NULL,
    name character varying(50),
    attribute integer
);


ALTER TABLE public.ref_storage OWNER TO pete;

--
-- Name: ref_storage_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_storage_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_storage_id_seq OWNER TO pete;

--
-- Name: ref_storage_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_storage_id_seq OWNED BY public.ref_storage.id;


--
-- Name: ref_territory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ref_territory (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.ref_territory OWNER TO postgres;

--
-- Name: ref_territory_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ref_territory_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_territory_id_seq OWNER TO postgres;

--
-- Name: ref_territory_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ref_territory_id_seq OWNED BY public.ref_territory.id;


--
-- Name: ref_type_city; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_type_city (
    id integer NOT NULL,
    name character varying(50)
);


ALTER TABLE public.ref_type_city OWNER TO pete;

--
-- Name: ref_type_city_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_type_city_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_type_city_id_seq OWNER TO pete;

--
-- Name: ref_type_city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_type_city_id_seq OWNED BY public.ref_type_city.id;


--
-- Name: ref_type_contragent; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_type_contragent (
    id bigint NOT NULL,
    name character varying(50)
);


ALTER TABLE public.ref_type_contragent OWNER TO pete;

--
-- Name: ref_type_contragent_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_type_contragent_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_type_contragent_id_seq OWNER TO pete;

--
-- Name: ref_type_contragent_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_type_contragent_id_seq OWNED BY public.ref_type_contragent.id;


--
-- Name: ref_type_doc; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_type_doc (
    id bigint NOT NULL,
    name character varying(50)
);


ALTER TABLE public.ref_type_doc OWNER TO pete;

--
-- Name: ref_type_doc_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_type_doc_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_type_doc_id_seq OWNER TO pete;

--
-- Name: ref_type_doc_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_type_doc_id_seq OWNED BY public.ref_type_doc.id;


--
-- Name: ref_type_lots; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.ref_type_lots (
    id bigint NOT NULL,
    name character varying(20)
);


ALTER TABLE public.ref_type_lots OWNER TO pete;

--
-- Name: ref_type_lots_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.ref_type_lots_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ref_type_lots_id_seq OWNER TO pete;

--
-- Name: ref_type_lots_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.ref_type_lots_id_seq OWNED BY public.ref_type_lots.id;


--
-- Name: table_current_price; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.table_current_price (
    id bigint NOT NULL,
    price_id bigint NOT NULL,
    lots_id bigint NOT NULL,
    cost real
);


ALTER TABLE public.table_current_price OWNER TO pete;

--
-- Name: table_current_price_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.table_current_price_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.table_current_price_id_seq OWNER TO pete;

--
-- Name: table_current_price_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.table_current_price_id_seq OWNED BY public.table_current_price.id;


--
-- Name: table_current_rest_stuff; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.table_current_rest_stuff (
    id bigint NOT NULL,
    storage_id bigint NOT NULL,
    nomenkl_id bigint NOT NULL,
    qty integer,
    sum real
);


ALTER TABLE public.table_current_rest_stuff OWNER TO pete;

--
-- Name: table_current_rest_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.table_current_rest_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.table_current_rest_id_seq OWNER TO pete;

--
-- Name: table_current_rest_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.table_current_rest_id_seq OWNED BY public.table_current_rest_stuff.id;


--
-- Name: table_current_rest_lots; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.table_current_rest_lots (
    id bigint NOT NULL,
    storage_id bigint NOT NULL,
    lots_id bigint,
    qty integer,
    sum real,
    plan_weight real
);


ALTER TABLE public.table_current_rest_lots OWNER TO pete;

--
-- Name: table_current_rest_lots_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.table_current_rest_lots_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.table_current_rest_lots_id_seq OWNER TO pete;

--
-- Name: table_current_rest_lots_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.table_current_rest_lots_id_seq OWNED BY public.table_current_rest_lots.id;


--
-- Name: table_doc_lots; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.table_doc_lots (
    id bigint NOT NULL,
    doc_id bigint NOT NULL,
    lots_id bigint NOT NULL,
    qty integer,
    sum real,
    weight real
);


ALTER TABLE public.table_doc_lots OWNER TO pete;

--
-- Name: table_doc_lots_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.table_doc_lots_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.table_doc_lots_id_seq OWNER TO pete;

--
-- Name: table_doc_lots_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.table_doc_lots_id_seq OWNED BY public.table_doc_lots.id;


--
-- Name: table_docs_stuff; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.table_docs_stuff (
    id bigint NOT NULL,
    doc_id bigint NOT NULL,
    nomenkl_id bigint NOT NULL,
    qty integer,
    sum real
);


ALTER TABLE public.table_docs_stuff OWNER TO pete;

--
-- Name: table_docs_stuff_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.table_docs_stuff_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.table_docs_stuff_id_seq OWNER TO pete;

--
-- Name: table_docs_stuff_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.table_docs_stuff_id_seq OWNED BY public.table_docs_stuff.id;


--
-- Name: table_history_price; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.table_history_price (
    id bigint NOT NULL,
    price_id bigint NOT NULL,
    lots_id bigint NOT NULL,
    date date NOT NULL,
    cost real
);


ALTER TABLE public.table_history_price OWNER TO pete;

--
-- Name: table_history_rest_stuff; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.table_history_rest_stuff (
    id bigint NOT NULL,
    storage_id bigint NOT NULL,
    nomenkl_id bigint NOT NULL,
    date date,
    qty integer,
    sum real
);


ALTER TABLE public.table_history_rest_stuff OWNER TO pete;

--
-- Name: table_history_rest_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.table_history_rest_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.table_history_rest_id_seq OWNER TO pete;

--
-- Name: table_history_rest_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.table_history_rest_id_seq OWNED BY public.table_history_rest_stuff.id;


--
-- Name: table_history_rest_lots; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.table_history_rest_lots (
    id bigint NOT NULL,
    storage_id bigint NOT NULL,
    lots_id bigint NOT NULL,
    qty integer,
    sum real,
    plan_weight real,
    fact_weight real,
    date date
);


ALTER TABLE public.table_history_rest_lots OWNER TO pete;

--
-- Name: table_history_rest_lots_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.table_history_rest_lots_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.table_history_rest_lots_id_seq OWNER TO pete;

--
-- Name: table_history_rest_lots_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.table_history_rest_lots_id_seq OWNED BY public.table_history_rest_lots.id;


--
-- Name: table_inventory_lots; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.table_inventory_lots (
    id bigint NOT NULL,
    inventory_doc_id bigint NOT NULL,
    lots_id bigint NOT NULL,
    qty_sys integer,
    qty_fact integer
);


ALTER TABLE public.table_inventory_lots OWNER TO pete;

--
-- Name: table_inventory_lots_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.table_inventory_lots_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.table_inventory_lots_id_seq OWNER TO pete;

--
-- Name: table_inventory_lots_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.table_inventory_lots_id_seq OWNED BY public.table_inventory_lots.id;


--
-- Name: table_inventory_stuff; Type: TABLE; Schema: public; Owner: pete
--

CREATE TABLE public.table_inventory_stuff (
    id bigint NOT NULL,
    inventory_doc_id bigint NOT NULL,
    nomenkl_id bigint NOT NULL,
    qty_sys integer,
    qty_fact integer
);


ALTER TABLE public.table_inventory_stuff OWNER TO pete;

--
-- Name: table_inventory_stuff_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.table_inventory_stuff_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.table_inventory_stuff_id_seq OWNER TO pete;

--
-- Name: table_inventory_stuff_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.table_inventory_stuff_id_seq OWNED BY public.table_inventory_stuff.id;


--
-- Name: table_operations_staff_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.table_operations_staff_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.table_operations_staff_id_seq OWNER TO pete;

--
-- Name: table_operations_staff_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.table_operations_staff_id_seq OWNED BY public.journal_operations_staff.id;


--
-- Name: table_price_id_seq; Type: SEQUENCE; Schema: public; Owner: pete
--

CREATE SEQUENCE public.table_price_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.table_price_id_seq OWNER TO pete;

--
-- Name: table_price_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pete
--

ALTER SEQUENCE public.table_price_id_seq OWNED BY public.table_history_price.id;


--
-- Name: doc_docs_head id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_docs_head ALTER COLUMN id SET DEFAULT nextval('public.doc_docs_head_id_seq'::regclass);


--
-- Name: doc_inventory id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_inventory ALTER COLUMN id SET DEFAULT nextval('public.doc_inventory_id_seq'::regclass);


--
-- Name: doc_pko id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_pko ALTER COLUMN id SET DEFAULT nextval('public.doc_pko_id_seq'::regclass);


--
-- Name: doc_rko id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_rko ALTER COLUMN id SET DEFAULT nextval('public.doc_rko_id_seq'::regclass);


--
-- Name: journal_care_operations id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_care_operations ALTER COLUMN id SET DEFAULT nextval('public.journal_care_operations_id_seq'::regclass);


--
-- Name: journal_operations_lots id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_operations_lots ALTER COLUMN id SET DEFAULT nextval('public.journal_operations_lots_id_seq'::regclass);


--
-- Name: journal_operations_staff id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_operations_staff ALTER COLUMN id SET DEFAULT nextval('public.table_operations_staff_id_seq'::regclass);


--
-- Name: journal_payments id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_payments ALTER COLUMN id SET DEFAULT nextval('public.journal_payments_id_seq'::regclass);


--
-- Name: journal_working_time id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_working_time ALTER COLUMN id SET DEFAULT nextval('public.journal_working_time_id_seq'::regclass);


--
-- Name: ref_city id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_city ALTER COLUMN id SET DEFAULT nextval('public.ref_city_id_seq'::regclass);


--
-- Name: ref_classification id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_classification ALTER COLUMN id SET DEFAULT nextval('public.ref_classification_id_seq'::regclass);


--
-- Name: ref_contragent id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_contragent ALTER COLUMN id SET DEFAULT nextval('public.ref_contragent_id_seq'::regclass);


--
-- Name: ref_employee id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_employee ALTER COLUMN id SET DEFAULT nextval('public.ref_employee_id_seq'::regclass);


--
-- Name: ref_kind_lots id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_kind_lots ALTER COLUMN id SET DEFAULT nextval('public.ref_kind_lots_id_seq'::regclass);


--
-- Name: ref_kind_of_type_doc id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_kind_of_type_doc ALTER COLUMN id SET DEFAULT nextval('public.ref_kind_of_type_id_seq'::regclass);


--
-- Name: ref_living id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_living ALTER COLUMN id SET DEFAULT nextval('public.ref_living_id_seq'::regclass);


--
-- Name: ref_lots id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_lots ALTER COLUMN id SET DEFAULT nextval('public.ref_lots_id_seq'::regclass);


--
-- Name: ref_market_view id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_market_view ALTER COLUMN id SET DEFAULT nextval('public.ref_market_view_id_seq'::regclass);


--
-- Name: ref_nomenkl id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_nomenkl ALTER COLUMN id SET DEFAULT nextval('public.ref_nomenkl_id_seq'::regclass);


--
-- Name: ref_passport id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_passport ALTER COLUMN id SET DEFAULT nextval('public.ref_passport_id_seq'::regclass);


--
-- Name: ref_pay_rates id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_pay_rates ALTER COLUMN id SET DEFAULT nextval('public.ref_pay_rates_id_seq'::regclass);


--
-- Name: ref_payments_type id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_payments_type ALTER COLUMN id SET DEFAULT nextval('public.ref_payments_type_id_seq'::regclass);


--
-- Name: ref_price id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_price ALTER COLUMN id SET DEFAULT nextval('public.ref_price_id_seq'::regclass);


--
-- Name: ref_roles id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_roles ALTER COLUMN id SET DEFAULT nextval('public.ref_roles_id_seq'::regclass);


--
-- Name: ref_size id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_size ALTER COLUMN id SET DEFAULT nextval('public.ref_size_id_seq'::regclass);


--
-- Name: ref_storage id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_storage ALTER COLUMN id SET DEFAULT nextval('public.ref_storage_id_seq'::regclass);


--
-- Name: ref_territory id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ref_territory ALTER COLUMN id SET DEFAULT nextval('public.ref_territory_id_seq'::regclass);


--
-- Name: ref_type_city id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_type_city ALTER COLUMN id SET DEFAULT nextval('public.ref_type_city_id_seq'::regclass);


--
-- Name: ref_type_contragent id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_type_contragent ALTER COLUMN id SET DEFAULT nextval('public.ref_type_contragent_id_seq'::regclass);


--
-- Name: ref_type_doc id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_type_doc ALTER COLUMN id SET DEFAULT nextval('public.ref_type_doc_id_seq'::regclass);


--
-- Name: ref_type_lots id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_type_lots ALTER COLUMN id SET DEFAULT nextval('public.ref_type_lots_id_seq'::regclass);


--
-- Name: table_current_price id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_current_price ALTER COLUMN id SET DEFAULT nextval('public.table_current_price_id_seq'::regclass);


--
-- Name: table_current_rest_lots id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_current_rest_lots ALTER COLUMN id SET DEFAULT nextval('public.table_current_rest_lots_id_seq'::regclass);


--
-- Name: table_current_rest_stuff id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_current_rest_stuff ALTER COLUMN id SET DEFAULT nextval('public.table_current_rest_id_seq'::regclass);


--
-- Name: table_doc_lots id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_doc_lots ALTER COLUMN id SET DEFAULT nextval('public.table_doc_lots_id_seq'::regclass);


--
-- Name: table_docs_stuff id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_docs_stuff ALTER COLUMN id SET DEFAULT nextval('public.table_docs_stuff_id_seq'::regclass);


--
-- Name: table_history_price id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_history_price ALTER COLUMN id SET DEFAULT nextval('public.table_price_id_seq'::regclass);


--
-- Name: table_history_rest_lots id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_history_rest_lots ALTER COLUMN id SET DEFAULT nextval('public.table_history_rest_lots_id_seq'::regclass);


--
-- Name: table_history_rest_stuff id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_history_rest_stuff ALTER COLUMN id SET DEFAULT nextval('public.table_history_rest_id_seq'::regclass);


--
-- Name: table_inventory_lots id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_inventory_lots ALTER COLUMN id SET DEFAULT nextval('public.table_inventory_lots_id_seq'::regclass);


--
-- Name: table_inventory_stuff id; Type: DEFAULT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_inventory_stuff ALTER COLUMN id SET DEFAULT nextval('public.table_inventory_stuff_id_seq'::regclass);


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
-- Data for Name: ref_classification; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_classification (id, parent_id, name) FROM stdin;
\.


--
-- Data for Name: ref_contragent; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_contragent (type_contra_id, city_id, id, price_id, market_view_id, name, address, contact, phone, comments) FROM stdin;
1	6	3	2	1	ATB	Ukraine	Direktor	067-123-45-66	Fucking olgarhs
\.


--
-- Data for Name: ref_employee; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_employee (id, roles_id, name, phone) FROM stdin;
\.


--
-- Data for Name: ref_kind_lots; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_kind_lots (id, name, type_lots_id) FROM stdin;
\.


--
-- Data for Name: ref_kind_of_type_doc; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_kind_of_type_doc (id, type_doc_id, name) FROM stdin;
\.


--
-- Data for Name: ref_living; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_living (id, employee_id, city_id, street, home, appartment, status) FROM stdin;
\.


--
-- Data for Name: ref_lots; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_lots (id, kind_lots_id, start_count, start_weight, start_age, number) FROM stdin;
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
-- Data for Name: ref_nomenkl; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_nomenkl (id, classification_id, size_id, name) FROM stdin;
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
-- Data for Name: ref_payments_type; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_payments_type (id, name) FROM stdin;
\.


--
-- Data for Name: ref_price; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_price (id, name) FROM stdin;
1	Wholesale
2	Retail
\.


--
-- Data for Name: ref_roles; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_roles (id, name) FROM stdin;
\.


--
-- Data for Name: ref_size; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_size (id, name) FROM stdin;
\.


--
-- Data for Name: ref_storage; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_storage (id, name, attribute) FROM stdin;
\.


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
-- Data for Name: ref_type_contragent; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_type_contragent (id, name) FROM stdin;
1	buyer
4	seller
\.


--
-- Data for Name: ref_type_doc; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_type_doc (id, name) FROM stdin;
\.


--
-- Data for Name: ref_type_lots; Type: TABLE DATA; Schema: public; Owner: pete
--

COPY public.ref_type_lots (id, name) FROM stdin;
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
-- Name: ref_classification classification_id; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_classification
    ADD CONSTRAINT classification_id PRIMARY KEY (id);


--
-- Name: ref_contragent contra_id; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_contragent
    ADD CONSTRAINT contra_id PRIMARY KEY (id);


--
-- Name: doc_docs_head doc_docs_head_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_docs_head
    ADD CONSTRAINT doc_docs_head_pkey PRIMARY KEY (id);


--
-- Name: doc_inventory doc_inventory_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_inventory
    ADD CONSTRAINT doc_inventory_pkey PRIMARY KEY (id);


--
-- Name: doc_pko doc_pko_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_pko
    ADD CONSTRAINT doc_pko_pkey PRIMARY KEY (id);


--
-- Name: doc_rko doc_rko_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_rko
    ADD CONSTRAINT doc_rko_pkey PRIMARY KEY (id);


--
-- Name: journal_care_operations journal_care_operations_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_care_operations
    ADD CONSTRAINT journal_care_operations_pkey PRIMARY KEY (id);


--
-- Name: journal_operations_lots journal_operations_lots_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_operations_lots
    ADD CONSTRAINT journal_operations_lots_pkey PRIMARY KEY (id);


--
-- Name: journal_payments journal_payments_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_payments
    ADD CONSTRAINT journal_payments_pkey PRIMARY KEY (id);


--
-- Name: journal_working_time journal_working_time_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_working_time
    ADD CONSTRAINT journal_working_time_pkey PRIMARY KEY (id);


--
-- Name: ref_lots lots_id; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_lots
    ADD CONSTRAINT lots_id PRIMARY KEY (id);


--
-- Name: ref_nomenkl nomenkl_id; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_nomenkl
    ADD CONSTRAINT nomenkl_id PRIMARY KEY (id);


--
-- Name: ref_price price_id; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_price
    ADD CONSTRAINT price_id PRIMARY KEY (id);


--
-- Name: ref_city ref_city_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_city
    ADD CONSTRAINT ref_city_pkey PRIMARY KEY (id);


--
-- Name: ref_employee ref_employee_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_employee
    ADD CONSTRAINT ref_employee_pkey PRIMARY KEY (id);


--
-- Name: ref_kind_lots ref_kind_lots_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_kind_lots
    ADD CONSTRAINT ref_kind_lots_pkey PRIMARY KEY (id);


--
-- Name: ref_kind_of_type_doc ref_kind_of_type_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_kind_of_type_doc
    ADD CONSTRAINT ref_kind_of_type_pkey PRIMARY KEY (id);


--
-- Name: ref_living ref_living_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_living
    ADD CONSTRAINT ref_living_pkey PRIMARY KEY (id);


--
-- Name: ref_market_view ref_market_view_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_market_view
    ADD CONSTRAINT ref_market_view_pkey PRIMARY KEY (id);


--
-- Name: ref_pay_rates ref_pay_rates_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_pay_rates
    ADD CONSTRAINT ref_pay_rates_pkey PRIMARY KEY (id);


--
-- Name: ref_payments_type ref_payments_type_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_payments_type
    ADD CONSTRAINT ref_payments_type_pkey PRIMARY KEY (id);


--
-- Name: ref_roles ref_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_roles
    ADD CONSTRAINT ref_roles_pkey PRIMARY KEY (id);


--
-- Name: ref_storage ref_storage_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_storage
    ADD CONSTRAINT ref_storage_pkey PRIMARY KEY (id);


--
-- Name: ref_territory ref_territory_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ref_territory
    ADD CONSTRAINT ref_territory_pkey PRIMARY KEY (id);


--
-- Name: ref_type_city ref_type_city_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_type_city
    ADD CONSTRAINT ref_type_city_pkey PRIMARY KEY (id);


--
-- Name: ref_type_doc ref_type_doc_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_type_doc
    ADD CONSTRAINT ref_type_doc_pkey PRIMARY KEY (id);


--
-- Name: ref_type_lots ref_type_lots_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_type_lots
    ADD CONSTRAINT ref_type_lots_pkey PRIMARY KEY (id);


--
-- Name: ref_size size_id; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_size
    ADD CONSTRAINT size_id PRIMARY KEY (id);


--
-- Name: table_current_price table_current_price_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_current_price
    ADD CONSTRAINT table_current_price_pkey PRIMARY KEY (id);


--
-- Name: table_current_rest_lots table_current_rest_lots_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_current_rest_lots
    ADD CONSTRAINT table_current_rest_lots_pkey PRIMARY KEY (id);


--
-- Name: table_current_rest_stuff table_current_rest_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_current_rest_stuff
    ADD CONSTRAINT table_current_rest_pkey PRIMARY KEY (id);


--
-- Name: table_doc_lots table_doc_lots_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_doc_lots
    ADD CONSTRAINT table_doc_lots_pkey PRIMARY KEY (id);


--
-- Name: table_docs_stuff table_docs_stuff_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_docs_stuff
    ADD CONSTRAINT table_docs_stuff_pkey PRIMARY KEY (id);


--
-- Name: table_history_rest_lots table_history_rest_lots_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_history_rest_lots
    ADD CONSTRAINT table_history_rest_lots_pkey PRIMARY KEY (id);


--
-- Name: table_history_rest_stuff table_history_rest_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_history_rest_stuff
    ADD CONSTRAINT table_history_rest_pkey PRIMARY KEY (id);


--
-- Name: table_inventory_lots table_inventory_lots_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_inventory_lots
    ADD CONSTRAINT table_inventory_lots_pkey PRIMARY KEY (id);


--
-- Name: table_inventory_stuff table_inventory_stuff_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_inventory_stuff
    ADD CONSTRAINT table_inventory_stuff_pkey PRIMARY KEY (id);


--
-- Name: journal_operations_staff table_operations_staff_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_operations_staff
    ADD CONSTRAINT table_operations_staff_pkey PRIMARY KEY (id);


--
-- Name: table_history_price table_price_pkey; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_history_price
    ADD CONSTRAINT table_price_pkey PRIMARY KEY (id);


--
-- Name: ref_type_contragent type_contra_id; Type: CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_type_contragent
    ADD CONSTRAINT type_contra_id PRIMARY KEY (id);


--
-- Name: ref_contragent city_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_contragent
    ADD CONSTRAINT city_id FOREIGN KEY (city_id) REFERENCES public.ref_city(id);


--
-- Name: ref_nomenkl classification_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_nomenkl
    ADD CONSTRAINT classification_id FOREIGN KEY (classification_id) REFERENCES public.ref_classification(id);


--
-- Name: doc_rko contra_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_rko
    ADD CONSTRAINT contra_id FOREIGN KEY (contra_id) REFERENCES public.ref_contragent(id);


--
-- Name: doc_pko contra_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_pko
    ADD CONSTRAINT contra_id FOREIGN KEY (contra_id) REFERENCES public.ref_contragent(id);


--
-- Name: doc_docs_head contra_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_docs_head
    ADD CONSTRAINT contra_id FOREIGN KEY (contra_id) REFERENCES public.ref_contragent(id);


--
-- Name: doc_pko doc_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_pko
    ADD CONSTRAINT doc_id FOREIGN KEY (doc_id) REFERENCES public.doc_docs_head(id);


--
-- Name: doc_rko doc_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_rko
    ADD CONSTRAINT doc_id FOREIGN KEY (doc_id) REFERENCES public.doc_docs_head(id);


--
-- Name: journal_operations_lots doc_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_operations_lots
    ADD CONSTRAINT doc_id FOREIGN KEY (doc_id) REFERENCES public.doc_docs_head(id);


--
-- Name: journal_operations_staff doc_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_operations_staff
    ADD CONSTRAINT doc_id FOREIGN KEY (doc_id) REFERENCES public.doc_docs_head(id);


--
-- Name: table_doc_lots doc_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_doc_lots
    ADD CONSTRAINT doc_id FOREIGN KEY (doc_id) REFERENCES public.doc_docs_head(id);


--
-- Name: table_docs_stuff doc_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_docs_stuff
    ADD CONSTRAINT doc_id FOREIGN KEY (doc_id) REFERENCES public.doc_docs_head(id);


--
-- Name: journal_working_time employee_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_working_time
    ADD CONSTRAINT employee_id FOREIGN KEY (employee_id) REFERENCES public.ref_employee(id);


--
-- Name: journal_payments employee_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_payments
    ADD CONSTRAINT employee_id FOREIGN KEY (employee_id) REFERENCES public.ref_employee(id);


--
-- Name: ref_living employee_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_living
    ADD CONSTRAINT employee_id FOREIGN KEY (employee_id) REFERENCES public.ref_employee(id);


--
-- Name: ref_passport employee_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_passport
    ADD CONSTRAINT employee_id FOREIGN KEY (employee_id) REFERENCES public.ref_employee(id);


--
-- Name: table_inventory_lots inventory_doc_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_inventory_lots
    ADD CONSTRAINT inventory_doc_id FOREIGN KEY (inventory_doc_id) REFERENCES public.doc_inventory(id);


--
-- Name: table_inventory_stuff inventory_doc_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_inventory_stuff
    ADD CONSTRAINT inventory_doc_id FOREIGN KEY (inventory_doc_id) REFERENCES public.doc_inventory(id);


--
-- Name: ref_lots kind_lots_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_lots
    ADD CONSTRAINT kind_lots_id FOREIGN KEY (kind_lots_id) REFERENCES public.ref_kind_lots(id);


--
-- Name: doc_docs_head kind_of_type_doc_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_docs_head
    ADD CONSTRAINT kind_of_type_doc_id FOREIGN KEY (kind_of_type_doc_id) REFERENCES public.ref_kind_of_type_doc(id);


--
-- Name: journal_operations_staff kind_type_doc_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_operations_staff
    ADD CONSTRAINT kind_type_doc_id FOREIGN KEY (kind_type_doc_id) REFERENCES public.ref_kind_of_type_doc(id);


--
-- Name: journal_operations_lots kind_type_doc_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_operations_lots
    ADD CONSTRAINT kind_type_doc_id FOREIGN KEY (kind_type_doc_id) REFERENCES public.ref_kind_of_type_doc(id);


--
-- Name: journal_operations_lots lots_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_operations_lots
    ADD CONSTRAINT lots_id FOREIGN KEY (lots_id) REFERENCES public.ref_lots(id);


--
-- Name: journal_care_operations lots_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_care_operations
    ADD CONSTRAINT lots_id FOREIGN KEY (lots_id) REFERENCES public.ref_lots(id);


--
-- Name: table_current_rest_lots lots_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_current_rest_lots
    ADD CONSTRAINT lots_id FOREIGN KEY (lots_id) REFERENCES public.ref_lots(id);


--
-- Name: table_doc_lots lots_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_doc_lots
    ADD CONSTRAINT lots_id FOREIGN KEY (lots_id) REFERENCES public.ref_lots(id);


--
-- Name: table_history_rest_lots lots_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_history_rest_lots
    ADD CONSTRAINT lots_id FOREIGN KEY (lots_id) REFERENCES public.ref_lots(id);


--
-- Name: table_inventory_lots lots_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_inventory_lots
    ADD CONSTRAINT lots_id FOREIGN KEY (lots_id) REFERENCES public.ref_lots(id);


--
-- Name: table_history_price lots_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_history_price
    ADD CONSTRAINT lots_id FOREIGN KEY (lots_id) REFERENCES public.ref_lots(id);


--
-- Name: table_current_price lots_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_current_price
    ADD CONSTRAINT lots_id FOREIGN KEY (lots_id) REFERENCES public.ref_lots(id);


--
-- Name: ref_contragent market_view_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_contragent
    ADD CONSTRAINT market_view_id FOREIGN KEY (market_view_id) REFERENCES public.ref_market_view(id);


--
-- Name: journal_care_operations nomekl_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_care_operations
    ADD CONSTRAINT nomekl_id FOREIGN KEY (nomenkl_id) REFERENCES public.ref_nomenkl(id);


--
-- Name: journal_operations_staff nomenkl_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_operations_staff
    ADD CONSTRAINT nomenkl_id FOREIGN KEY (nomenkl_id) REFERENCES public.ref_nomenkl(id);


--
-- Name: table_current_rest_stuff nomenkl_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_current_rest_stuff
    ADD CONSTRAINT nomenkl_id FOREIGN KEY (nomenkl_id) REFERENCES public.ref_nomenkl(id);


--
-- Name: table_docs_stuff nomenkl_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_docs_stuff
    ADD CONSTRAINT nomenkl_id FOREIGN KEY (nomenkl_id) REFERENCES public.ref_nomenkl(id);


--
-- Name: table_history_rest_stuff nomenkl_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_history_rest_stuff
    ADD CONSTRAINT nomenkl_id FOREIGN KEY (nomenkl_id) REFERENCES public.ref_nomenkl(id);


--
-- Name: table_inventory_stuff nomenkl_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_inventory_stuff
    ADD CONSTRAINT nomenkl_id FOREIGN KEY (nomenkl_id) REFERENCES public.ref_nomenkl(id);


--
-- Name: ref_classification parent_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_classification
    ADD CONSTRAINT parent_id FOREIGN KEY (parent_id) REFERENCES public.ref_classification(id);


--
-- Name: ref_pay_rates payments_type_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_pay_rates
    ADD CONSTRAINT payments_type_id FOREIGN KEY (payments_type_id) REFERENCES public.ref_payments_type(id);


--
-- Name: journal_payments payments_type_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_payments
    ADD CONSTRAINT payments_type_id FOREIGN KEY (payments_type_id) REFERENCES public.ref_payments_type(id);


--
-- Name: ref_contragent price_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_contragent
    ADD CONSTRAINT price_id FOREIGN KEY (price_id) REFERENCES public.ref_price(id);


--
-- Name: table_history_price price_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_history_price
    ADD CONSTRAINT price_id FOREIGN KEY (price_id) REFERENCES public.ref_price(id);


--
-- Name: table_current_price price_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_current_price
    ADD CONSTRAINT price_id FOREIGN KEY (price_id) REFERENCES public.ref_price(id);


--
-- Name: ref_pay_rates roles_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_pay_rates
    ADD CONSTRAINT roles_id FOREIGN KEY (roles_id) REFERENCES public.ref_roles(id);


--
-- Name: ref_employee roles_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_employee
    ADD CONSTRAINT roles_id FOREIGN KEY (roles_id) REFERENCES public.ref_roles(id);


--
-- Name: ref_nomenkl size_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_nomenkl
    ADD CONSTRAINT size_id FOREIGN KEY (size_id) REFERENCES public.ref_size(id);


--
-- Name: journal_operations_staff storage_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_operations_staff
    ADD CONSTRAINT storage_id FOREIGN KEY (storage_id) REFERENCES public.ref_storage(id);


--
-- Name: journal_operations_lots storage_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.journal_operations_lots
    ADD CONSTRAINT storage_id FOREIGN KEY (storage_id) REFERENCES public.ref_storage(id);


--
-- Name: doc_inventory storage_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_inventory
    ADD CONSTRAINT storage_id FOREIGN KEY (storage_id) REFERENCES public.ref_storage(id);


--
-- Name: table_current_rest_lots storage_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_current_rest_lots
    ADD CONSTRAINT storage_id FOREIGN KEY (storage_id) REFERENCES public.ref_storage(id);


--
-- Name: table_current_rest_stuff storage_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_current_rest_stuff
    ADD CONSTRAINT storage_id FOREIGN KEY (storage_id) REFERENCES public.ref_storage(id);


--
-- Name: table_history_rest_lots storage_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_history_rest_lots
    ADD CONSTRAINT storage_id FOREIGN KEY (storage_id) REFERENCES public.ref_storage(id);


--
-- Name: table_history_rest_stuff storage_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.table_history_rest_stuff
    ADD CONSTRAINT storage_id FOREIGN KEY (storage_id) REFERENCES public.ref_storage(id);


--
-- Name: doc_docs_head storage_in_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_docs_head
    ADD CONSTRAINT storage_in_id FOREIGN KEY (storage_in_id) REFERENCES public.ref_storage(id);


--
-- Name: doc_docs_head storage_out_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.doc_docs_head
    ADD CONSTRAINT storage_out_id FOREIGN KEY (storage_out_id) REFERENCES public.ref_storage(id);


--
-- Name: ref_city ter_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_city
    ADD CONSTRAINT ter_id FOREIGN KEY (ter_id) REFERENCES public.ref_territory(id);


--
-- Name: ref_city type_city_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_city
    ADD CONSTRAINT type_city_id FOREIGN KEY (type_city_id) REFERENCES public.ref_type_city(id);


--
-- Name: ref_contragent type_contra_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_contragent
    ADD CONSTRAINT type_contra_id FOREIGN KEY (type_contra_id) REFERENCES public.ref_type_contragent(id);


--
-- Name: ref_kind_of_type_doc type_doc_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_kind_of_type_doc
    ADD CONSTRAINT type_doc_id FOREIGN KEY (type_doc_id) REFERENCES public.ref_type_doc(id);


--
-- Name: ref_kind_lots type_lots_id; Type: FK CONSTRAINT; Schema: public; Owner: pete
--

ALTER TABLE ONLY public.ref_kind_lots
    ADD CONSTRAINT type_lots_id FOREIGN KEY (type_lots_id) REFERENCES public.ref_type_lots(id);


--
-- PostgreSQL database dump complete
--

