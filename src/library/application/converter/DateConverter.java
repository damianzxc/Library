package library.application.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter("myDateConverter")
public class DateConverter extends DateTimeConverter {

	public DateConverter() {
		setPattern("dd/MM/yyyy");
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return super.getAsObject(context, component, value);
	}
}
