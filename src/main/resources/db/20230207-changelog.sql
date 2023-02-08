CREATE TABLE product(
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  code VARCHAR(10) NOT NULL UNIQUE,
  price_hrk NUMERIC(14, 4) NOT NULL,
  price_eur NUMERIC(14, 4) NOT NULL,
  available BOOLEAN NOT NULL,
  CONSTRAINT pk_product PRIMARY KEY (id));

INSERT INTO public.product(name, description, code, price_hrk, price_eur, available) VALUES ('Tape', 'Tape description', '1234567891', 37.5, 5, true);
INSERT INTO public.product(name, description, code, price_hrk, price_eur, available) VALUES ('Adapter', 'Adapter description', '1122334455', 30, 4, true);
INSERT INTO public.product(name, description, code, price_hrk, price_eur, available) VALUES ('Tomato', 'Tomato description', '9999911111', 15, 2, true);
INSERT INTO public.product(name, description, code, price_hrk, price_eur, available) VALUES ('Fabric softener', 'Fabric softener description', '4444333322', 60, 8, false);
INSERT INTO public.product(name, description, code, price_hrk, price_eur, available) VALUES ('Cooking dish', 'Cooking dish description', '1234123412', 187.5, 25, false);