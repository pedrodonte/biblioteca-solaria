--select prestamo_cod_estado, count(1) from sola.sola_tab_prestamo group by prestamo_cod_estado
--select prestamo_fec_update::date, count(1) from sola.sola_tab_prestamo group by prestamo_fec_update::date order by prestamo_fec_update::date
select prestamo_fec_update::date, count(1) from sola.sola_tab_prestamo group by prestamo_fec_update::date order by prestamo_fec_update::date