import java.io.Serializable;

public class LxnEntity implements Serializable {
    private String index;
    private String sampleName;
    private String sampleType;
    private String componentName;
    private String ComponentType;
    private String ComponentGroupName;
    private String actualConcentration;
    private String expectedRT;
    private String area;
    private String isArea;
    private String retentionTime;
    private String retentionTimeDelta;
    private boolean used;
    private String calculatedConcentration;
    private String adductCharge;
    private String accuracy;
    private String formula;
    private String precursorMass;
    private String accuracyAcceptance;
    private String concentrationAcceptance;
    private boolean Reportable;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentType() {
        return ComponentType;
    }

    public void setComponentType(String componentType) {
        ComponentType = componentType;
    }

    public String getComponentGroupName() {
        return ComponentGroupName;
    }

    public void setComponentGroupName(String componentGroupName) {
        ComponentGroupName = componentGroupName;
    }

    public String getActualConcentration() {
        return actualConcentration;
    }

    public void setActualConcentration(String actualConcentration) {
        this.actualConcentration = actualConcentration;
    }

    public String getExpectedRT() {
        return expectedRT;
    }

    public void setExpectedRT(String expectedRT) {
        this.expectedRT = expectedRT;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIsArea() {
        return isArea;
    }

    public void setIsArea(String isArea) {
        this.isArea = isArea;
    }

    public String getRetentionTime() {
        return retentionTime;
    }

    public void setRetentionTime(String retentionTime) {
        this.retentionTime = retentionTime;
    }

    public String getRetentionTimeDelta() {
        return retentionTimeDelta;
    }

    public void setRetentionTimeDelta(String retentionTimeDelta) {
        this.retentionTimeDelta = retentionTimeDelta;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getCalculatedConcentration() {
        return calculatedConcentration;
    }

    public void setCalculatedConcentration(String calculatedConcentration) {
        this.calculatedConcentration = calculatedConcentration;
    }

    public String getAdductCharge() {
        return adductCharge;
    }

    public void setAdductCharge(String adductCharge) {
        this.adductCharge = adductCharge;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getPrecursorMass() {
        return precursorMass;
    }

    public void setPrecursorMass(String precursorMass) {
        this.precursorMass = precursorMass;
    }

    public String getAccuracyAcceptance() {
        return accuracyAcceptance;
    }

    public void setAccuracyAcceptance(String accuracyAcceptance) {
        this.accuracyAcceptance = accuracyAcceptance;
    }

    public String getConcentrationAcceptance() {
        return concentrationAcceptance;
    }

    public void setConcentrationAcceptance(String concentrationAcceptance) {
        this.concentrationAcceptance = concentrationAcceptance;
    }

    public boolean isReportable() {
        return Reportable;
    }

    public void setReportable(boolean reportable) {
        Reportable = reportable;
    }
}
