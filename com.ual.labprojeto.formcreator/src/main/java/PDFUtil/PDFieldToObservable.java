package PDFUtil;

import org.apache.pdfbox.pdmodel.interactive.form.PDChoice;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class PDFieldToObservable {



    public static ObservablePdfFields pdfieldToObsPdfField(PDField pdField){
        ObservablePdfFields obsField;
        if(pdField.getFieldType()=="Ch"){
            PDChoice chField = (PDChoice) pdField;
            obsField= new ObservablePdfFields(
                    chField.getFullyQualifiedName()
                    ,chField.getClass().getSimpleName().replace(" ","")
                    ,chField.getAlternateFieldName()
                    ,chField.getValueAsString()
                    ,chField.getDefaultValue().toString()
                    ,chField.getOptionsDisplayValues());

        }
        else{
            obsField = new ObservablePdfFields(
                    pdField.getFullyQualifiedName()
                    ,pdField.getClass().getSimpleName().replace("PD","")
                    ,pdField.getAlternateFieldName()
                    ,pdField.getValueAsString());
        }
        return obsField;
    }

}
