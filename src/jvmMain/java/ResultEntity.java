import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import java.io.Serializable;
@ColumnWidth(20)
public class ResultEntity  implements Serializable {
    @ExcelProperty("Sample Name")
    private String sampleName;
    @ExcelProperty(Const.FTS42_1)
    private String FTS42_1;
    @ExcelProperty(Const.ClPFESA62_1)
    private String ClPFESA62_1;
    @ExcelProperty(Const.FTS62_1)
    private String FTS62_1;
    @ExcelProperty(Const.ClPFESA82_1)
    private String ClPFESA82_1;
    @ExcelProperty(Const.FTS82_1)
    private String FTS82_1;
    @ExcelProperty(Const.FOSAI_1)
    private String FOSAI_1;
    @ExcelProperty(Const.HFPODA_1)
    private String HFPODA_1;
    @ExcelProperty(Const.NEtFOSAA_1)
    private String NEtFOSAA_1;
    @ExcelProperty(Const.NMeFOSAA_1)
    private String NMeFOSAA_1;
    @ExcelProperty(Const.NaDONA_1)
    private String NaDONA_1;
    @ExcelProperty(Const.PFBA)
    private String PFBA;
    @ExcelProperty(Const.PFBS_1)
    private String PFBS_1;
    @ExcelProperty(Const.PFDA_1)
    private String PFDA_1;
    @ExcelProperty(Const.PFDoA_1)
    private String PFDoA_1;
    @ExcelProperty(Const.PFDS_1)
    private String PFDS_1;
    @ExcelProperty(Const.PFHpA_1)
    private String PFHpA_1;
    @ExcelProperty(Const.PFHpS_1)
    private String PFHpS_1;
    @ExcelProperty(Const.PFHxA_1)
    private String PFHxA_1;
    @ExcelProperty(Const.PFHxS_11)
    private String PFHxS_11;
    @ExcelProperty(Const.PFNA_1)
    private String PFNA_1;
    @ExcelProperty(Const.PFNS_1)
    private String PFNS_1;
    @ExcelProperty(Const.PFOA_1)
    private String PFOA_1;
    @ExcelProperty(Const.PFOS_1)
    private String PFOS_1;
    @ExcelProperty(Const.PFPeA_1)
    private String PFPeA_1;
    @ExcelProperty(Const.PFPeS_1)
    private String PFPeS_1;
    @ExcelProperty(Const.PFTeDA_1)
    private String PFTeDA_1;
    @ExcelProperty(Const.PFTrDA_1)
    private String PFTrDA_1;
    @ExcelProperty(Const.PFUdA_1)
    private String PFUdA_1;
    @ExcelProperty(Const.PFOS_21)
    private String PFOS_21;
    @ExcelProperty(Const.PFOS_31)
    private String PFOS_31;
    @ExcelProperty(Const.PFOS_5)
    private String PFOS_5;
    @ExcelProperty(Const.PFHxS_21)
    private String PFHxS_21;

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getFTS42_1() {
        return FTS42_1;
    }

    public void setFTS42_1(String FTS42_1) {
        this.FTS42_1 = FTS42_1;
    }

    public String getClPFESA62_1() {
        return ClPFESA62_1;
    }

    public void setClPFESA62_1(String clPFESA62_1) {
        ClPFESA62_1 = clPFESA62_1;
    }

    public String getFTS62_1() {
        return FTS62_1;
    }

    public void setFTS62_1(String FTS62_1) {
        this.FTS62_1 = FTS62_1;
    }

    public String getClPFESA82_1() {
        return ClPFESA82_1;
    }

    public void setClPFESA82_1(String clPFESA82_1) {
        ClPFESA82_1 = clPFESA82_1;
    }

    public String getFTS82_1() {
        return FTS82_1;
    }

    public void setFTS82_1(String FTS82_1) {
        this.FTS82_1 = FTS82_1;
    }

    public String getFOSAI_1() {
        return FOSAI_1;
    }

    public void setFOSAI_1(String FOSAI_1) {
        this.FOSAI_1 = FOSAI_1;
    }

    public String getHFPODA_1() {
        return HFPODA_1;
    }

    public void setHFPODA_1(String HFPODA_1) {
        this.HFPODA_1 = HFPODA_1;
    }

    public String getNEtFOSAA_1() {
        return NEtFOSAA_1;
    }

    public void setNEtFOSAA_1(String NEtFOSAA_1) {
        this.NEtFOSAA_1 = NEtFOSAA_1;
    }

    public String getNMeFOSAA_1() {
        return NMeFOSAA_1;
    }

    public void setNMeFOSAA_1(String NMeFOSAA_1) {
        this.NMeFOSAA_1 = NMeFOSAA_1;
    }

    public String getNaDONA_1() {
        return NaDONA_1;
    }

    public void setNaDONA_1(String naDONA_1) {
        NaDONA_1 = naDONA_1;
    }

    public String getPFBA() {
        return PFBA;
    }

    public void setPFBA(String PFBA) {
        this.PFBA = PFBA;
    }

    public String getPFBS_1() {
        return PFBS_1;
    }

    public void setPFBS_1(String PFBS_1) {
        this.PFBS_1 = PFBS_1;
    }

    public String getPFDA_1() {
        return PFDA_1;
    }

    public void setPFDA_1(String PFDA_1) {
        this.PFDA_1 = PFDA_1;
    }

    public String getPFDoA_1() {
        return PFDoA_1;
    }

    public void setPFDoA_1(String PFDoA_1) {
        this.PFDoA_1 = PFDoA_1;
    }

    public String getPFDS_1() {
        return PFDS_1;
    }

    public void setPFDS_1(String PFDS_1) {
        this.PFDS_1 = PFDS_1;
    }

    public String getPFHpA_1() {
        return PFHpA_1;
    }

    public void setPFHpA_1(String PFHpA_1) {
        this.PFHpA_1 = PFHpA_1;
    }

    public String getPFHpS_1() {
        return PFHpS_1;
    }

    public void setPFHpS_1(String PFHpS_1) {
        this.PFHpS_1 = PFHpS_1;
    }

    public String getPFHxA_1() {
        return PFHxA_1;
    }

    public void setPFHxA_1(String PFHxA_1) {
        this.PFHxA_1 = PFHxA_1;
    }

    public String getPFHxS_11() {
        return PFHxS_11;
    }

    public void setPFHxS_11(String PFHxS_11) {
        this.PFHxS_11 = PFHxS_11;
    }

    public String getPFNA_1() {
        return PFNA_1;
    }

    public void setPFNA_1(String PFNA_1) {
        this.PFNA_1 = PFNA_1;
    }

    public String getPFNS_1() {
        return PFNS_1;
    }

    public void setPFNS_1(String PFNS_1) {
        this.PFNS_1 = PFNS_1;
    }

    public String getPFOA_1() {
        return PFOA_1;
    }

    public void setPFOA_1(String PFOA_1) {
        this.PFOA_1 = PFOA_1;
    }

    public String getPFOS_1() {
        return PFOS_1;
    }

    public void setPFOS_1(String PFOS_1) {
        this.PFOS_1 = PFOS_1;
    }

    public String getPFPeA_1() {
        return PFPeA_1;
    }

    public void setPFPeA_1(String PFPeA_1) {
        this.PFPeA_1 = PFPeA_1;
    }

    public String getPFPeS_1() {
        return PFPeS_1;
    }

    public void setPFPeS_1(String PFPeS_1) {
        this.PFPeS_1 = PFPeS_1;
    }

    public String getPFTeDA_1() {
        return PFTeDA_1;
    }

    public void setPFTeDA_1(String PFTeDA_1) {
        this.PFTeDA_1 = PFTeDA_1;
    }

    public String getPFTrDA_1() {
        return PFTrDA_1;
    }

    public void setPFTrDA_1(String PFTrDA_1) {
        this.PFTrDA_1 = PFTrDA_1;
    }

    public String getPFUdA_1() {
        return PFUdA_1;
    }

    public void setPFUdA_1(String PFUdA_1) {
        this.PFUdA_1 = PFUdA_1;
    }

    public String getPFOS_21() {
        return PFOS_21;
    }

    public void setPFOS_21(String PFOS_21) {
        this.PFOS_21 = PFOS_21;
    }

    public String getPFOS_31() {
        return PFOS_31;
    }

    public void setPFOS_31(String PFOS_31) {
        this.PFOS_31 = PFOS_31;
    }

    public String getPFOS_5() {
        return PFOS_5;
    }

    public void setPFOS_5(String PFOS_5) {
        this.PFOS_5 = PFOS_5;
    }

    public String getPFHxS_21() {
        return PFHxS_21;
    }

    public void setPFHxS_21(String PFHxS_21) {
        this.PFHxS_21 = PFHxS_21;
    }
}
