package com.mz.xpos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mz.xpos.dao.Facturas;
import com.mz.xpos.model.Factura;
import com.mz.xpos.util.cdi.CDIServiceLocator;


@FacesConverter(forClass = Factura.class)
public class FacturaConverter implements Converter {

	//@Inject
	private Facturas facturas;
	
	public FacturaConverter() {
		facturas = CDIServiceLocator.getBean(Facturas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Factura retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = facturas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Factura factura = (Factura) value;
			return factura.getId() == null ? null : factura.getId().toString();
		}
		
		return "";
	}

}
