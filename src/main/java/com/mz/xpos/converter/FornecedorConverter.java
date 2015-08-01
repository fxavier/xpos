/**
 * 
 */
package com.mz.xpos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mz.xpos.dao.Fornecedores;
import com.mz.xpos.model.Fornecedor;
import com.mz.xpos.util.cdi.CDIServiceLocator;

/**
 * @author Dell
 *
 */
@FacesConverter(forClass = Fornecedor.class)
public class FornecedorConverter implements Converter{
	
	private Fornecedores fornecedores;
	
	public FornecedorConverter() {
		fornecedores = CDIServiceLocator.getBean(Fornecedores.class);
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Fornecedor retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = fornecedores.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if(value != null){
			Long codigo = ((Fornecedor)value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		//	Fornecedor fornecedor = (Fornecedor)value;
		//	return fornecedor.getId() == null ? null : fornecedor.getId().toString();
		}
		return "";
	}

}
