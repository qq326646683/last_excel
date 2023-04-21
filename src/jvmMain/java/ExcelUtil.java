import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtil {
    public static String test() {
        return "yyy";
    }

    public static void readExcel(String path) {
        EasyExcel.read(path, DemoData.class, new ReadListener<DemoData>() {
            /**
             * 单次缓存的数据量
             */
            public static final int BATCH_COUNT = 5;
            /**
             *临时存储
             */
            private List<DemoData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

            @Override
            public void invoke(DemoData data, AnalysisContext context) {
                cachedDataList.add(data);
                if (cachedDataList.size() >= BATCH_COUNT) {
                    saveData();
                    // 存储完成清理 list
                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                saveData();
            }

            /**
             * 加上存储数据库
             */
            private void saveData() {
                for (DemoData demoData : cachedDataList) {

                }
            }
        }).sheet().doRead();

    }

    public static void readLxnExcel1(String path, String savePath) {
        EasyExcel.read(path, LxnEntity.class, new ReadListener<LxnEntity>() {
            /**
             * 单次缓存的数据量
             */
            public static final int BATCH_COUNT = 100000;
            /**
             *临时存储
             */
            private List<LxnEntity> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

            @Override
            public void invoke(LxnEntity data, AnalysisContext context) {
                cachedDataList.add(data);
                if (cachedDataList.size() >= BATCH_COUNT) {
                    saveData();
                    // 存储完成清理 list
                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                saveData();
            }

            /**
             * 加上存储数据库
             */
            private void saveData() {
                dealLxnExcel1(cachedDataList, savePath);
            }
        }).sheet().doRead();

    }

    private static void dealLxnExcel1(List<LxnEntity> cachedDataList, String savePath) {
        List<ResultEntity> resultEntityList1 = new ArrayList<>();
        List<ResultEntity> resultEntityList2 = new ArrayList<>();
        List<ResultEntity> resultEntityList3 = new ArrayList<>();
        for (LxnEntity lxnEntity : cachedDataList) {
            //表1: 结果列表ResultEntity里无该SampleName对象，且不在SampleName黑名单里，就创建ResultEntity对象
            if (!containSampleNameResultObj(resultEntityList1, lxnEntity) && !isInSampleNameBlackList(lxnEntity) && Const.SampleType.equals(lxnEntity.getSampleType())) {
                ResultEntity resultEntity = new ResultEntity();
                resultEntity.setSampleName(lxnEntity.getSampleName());
                resultEntityList1.add(resultEntity);
            }

            ResultEntity resultEntity1 = getResultEntity(resultEntityList1, lxnEntity.getSampleName());
            if (resultEntity1 != null) {
                String calc = lxnEntity.getCalculatedConcentration();
                setAllCalc(lxnEntity.getComponentName(), calc, resultEntity1);
            }

            //表2: 结果列表ResultEntity里无该SampleName对象，且在SampleName白名单里，就创建ResultEntity对象
            if (!containSampleNameResultObj(resultEntityList2, lxnEntity) && isInSampleNameWhiteList(lxnEntity) && Const.SampleType.equals(lxnEntity.getSampleType())) {
                ResultEntity resultEntity = new ResultEntity();
                resultEntity.setSampleName(lxnEntity.getSampleName());
                resultEntityList2.add(resultEntity);
            }

            ResultEntity resultEntity2 = getResultEntity(resultEntityList2, lxnEntity.getSampleName());
            if (resultEntity2 != null) {
                String calc = lxnEntity.getCalculatedConcentration();
                setAllCalc(lxnEntity.getComponentName(), calc, resultEntity2);
            }
        }

        try (ExcelWriter excelWriter = EasyExcel.write(savePath).build()) {
            WriteSheet writeSheet1 = EasyExcel.writerSheet(0, "raw").head(ResultEntity.class).build();
            excelWriter.write(resultEntityList1, writeSheet1);
            WriteSheet writeSheet2 = EasyExcel.writerSheet(1, "K&R").head(ResultEntity.class).build();
            excelWriter.write(resultEntityList2, writeSheet2);


            //表3: 先获取K1对象
            /*
            加一个表格Result
                1.所有物质结果用Raw的值-K1  ***PFTrDA /2(的K1值也需要除以2）***

                2.4:2FTS 	6:2FTS 	8:2FTS 	FOSA 	N-EtFOSAA 	N-MeFOSAA 	PFDS 	PFNS 	PFPeS  8:2 ClPFESA  PFHxA  HFPO-DA 1 PFBS 1 PFDS 1 PFPeA 1 PFTeDA 1 ***这些物质的K1全部定义为0***

                   减完K1值后
                3.PFOS1*0.788  PFOS 21*0.1  PFOS3*0.086  PFOS5*0.012
                PFHxS 11*0.811  PFHxS21*0.189
                PFTrDA /2

                4.结果小于0都=0
            */
            ResultEntity k1Entity = getResultEntity(resultEntityList2, "k1");
            if (k1Entity == null) {
                k1Entity = getResultEntity(resultEntityList2, "K1");
            }
            setK1CalcToZero(k1Entity);
            resultEntityList3.addAll(resultEntityList1);
            for (ResultEntity resultEntity : resultEntityList3) {
                dealTable3(resultEntity, k1Entity);
            }
            WriteSheet writeSheet3 = EasyExcel.writerSheet(2, "Result").head(ResultEntity.class).build();
            excelWriter.write(resultEntityList3, writeSheet3);
        }

    }

    private static void setAllCalc(String componentName, String calc, ResultEntity resultEntity) {
        switch (componentName) {
            case Const.FTS42_1:
                resultEntity.setFTS42_1(calc);
                break;
            case Const.ClPFESA62_1:
                resultEntity.setClPFESA62_1(calc);
                break;
            case Const.FTS62_1:
                resultEntity.setFTS62_1(calc);
                break;
            case Const.ClPFESA82_1:
                resultEntity.setClPFESA82_1(calc);
                break;
            case Const.FTS82_1:
                resultEntity.setFTS82_1(calc);
                break;
            case Const.FOSAI_1:
                resultEntity.setFOSAI_1(calc);
                break;
            case Const.HFPODA_1:
                resultEntity.setHFPODA_1(calc);
                break;
            case Const.NEtFOSAA_1:
                resultEntity.setNEtFOSAA_1(calc);
                break;
            case Const.NMeFOSAA_1:
                resultEntity.setNMeFOSAA_1(calc);
                break;
            case Const.NaDONA_1:
                resultEntity.setNaDONA_1(calc);
                break;
            case Const.PFBA:
                resultEntity.setPFBA(calc);
                break;
            case Const.PFBS_1:
                resultEntity.setPFBS_1(calc);
                break;
            case Const.PFDA_1:
                resultEntity.setPFDA_1(calc);
                break;
            case Const.PFDoA_1:
                resultEntity.setPFDoA_1(calc);
                break;
            case Const.PFDS_1:
                resultEntity.setPFDS_1(calc);
                break;
            case Const.PFHpA_1:
                resultEntity.setPFHpA_1(calc);
                break;
            case Const.PFHpS_1:
                resultEntity.setPFHpS_1(calc);
                break;
            case Const.PFHxA_1:
                resultEntity.setPFHxA_1(calc);
                break;
            case Const.PFHxS_11:
                resultEntity.setPFHxS_11(calc);
                break;
            case Const.PFNA_1:
                resultEntity.setPFNA_1(calc);
                break;
            case Const.PFNS_1:
                resultEntity.setPFNS_1(calc);
                break;
            case Const.PFOA_1:
                resultEntity.setPFOA_1(calc);
                break;
            case Const.PFOS_1:
                resultEntity.setPFOS_1(calc);
                break;
            case Const.PFPeA_1:
                resultEntity.setPFPeA_1(calc);
                break;
            case Const.PFPeS_1:
                resultEntity.setPFPeS_1(calc);
                break;
            case Const.PFTeDA_1:
                resultEntity.setPFTeDA_1(calc);
                break;
            case Const.PFTrDA_1:
                resultEntity.setPFTrDA_1(calc);
                break;
            case Const.PFUdA_1:
                resultEntity.setPFUdA_1(calc);
                break;
            case Const.PFOS_21:
                resultEntity.setPFOS_21(calc);
                break;
            case Const.PFOS_31:
                resultEntity.setPFOS_31(calc);
                break;
            case Const.PFOS_5:
                resultEntity.setPFOS_5(calc);
                break;
            case Const.PFHxS_21:
                resultEntity.setPFHxS_21(calc);
                break;
        }
    }

    private static void setK1CalcToZero(ResultEntity k1Entity) {
        k1Entity.setFTS42_1("0");
        k1Entity.setFTS62_1("0");
        k1Entity.setFTS82_1("0");
        k1Entity.setFOSAI_1("0");
        k1Entity.setNEtFOSAA_1("0");
        k1Entity.setNMeFOSAA_1("0");
        k1Entity.setPFDS_1("0");
        k1Entity.setPFNS_1("0");
        k1Entity.setPFPeS_1("0");
        k1Entity.setClPFESA82_1("0");
        k1Entity.setPFHxA_1("0");
        k1Entity.setHFPODA_1("0");
        k1Entity.setPFBS_1("0");
        k1Entity.setPFDS_1("0");
        k1Entity.setPFPeA_1("0");
        k1Entity.setPFTeDA_1("0");

        String calcValue = k1Entity.getPFTrDA_1();
        double pFTrDA_1 = stringToDouble(calcValue) / 2;
        k1Entity.setPFTrDA_1(String.valueOf(pFTrDA_1));
    }

    public static void dealTable3(ResultEntity targetEntity, ResultEntity k1Entity) {
        double tmpFTS42_1 = stringToDouble(targetEntity.getFTS42_1()) - stringToDouble(k1Entity.getFTS42_1());
        targetEntity.setFTS42_1(String.valueOf(Math.max(0, tmpFTS42_1)));
        double tmpClPFESA62_1 = stringToDouble(targetEntity.getClPFESA62_1()) - stringToDouble(k1Entity.getClPFESA62_1());
        targetEntity.setClPFESA62_1(String.valueOf(Math.max(0, tmpClPFESA62_1)));
        double tmpFTS62_1 = stringToDouble(targetEntity.getFTS62_1()) - stringToDouble(k1Entity.getFTS62_1());
        targetEntity.setFTS62_1(String.valueOf(Math.max(0, tmpFTS62_1)));
        double tmpClPFESA82_1 = stringToDouble(targetEntity.getClPFESA82_1()) - stringToDouble(k1Entity.getClPFESA82_1());
        targetEntity.setClPFESA82_1(String.valueOf(Math.max(0, tmpClPFESA82_1)));
        double tmpFTS82_1 = stringToDouble(targetEntity.getFTS82_1()) - stringToDouble(k1Entity.getFTS82_1());
        targetEntity.setFTS82_1(String.valueOf(Math.max(0, tmpFTS82_1)));
        double tmpFOSAI_1 = stringToDouble(targetEntity.getFOSAI_1()) - stringToDouble(k1Entity.getFOSAI_1());
        targetEntity.setFOSAI_1(String.valueOf(Math.max(0, tmpFOSAI_1)));
        double tmpHFPODA_1 = stringToDouble(targetEntity.getHFPODA_1()) - stringToDouble(k1Entity.getHFPODA_1());
        targetEntity.setHFPODA_1(String.valueOf(Math.max(0, tmpHFPODA_1)));
        double tmpNEtFOSAA_1 = stringToDouble(targetEntity.getNEtFOSAA_1()) - stringToDouble(k1Entity.getNEtFOSAA_1());
        targetEntity.setNEtFOSAA_1(String.valueOf(Math.max(0, tmpNEtFOSAA_1)));
        double tmpNMeFOSAA_1 = stringToDouble(targetEntity.getNMeFOSAA_1()) - stringToDouble(k1Entity.getNMeFOSAA_1());
        targetEntity.setNMeFOSAA_1(String.valueOf(Math.max(0, tmpNMeFOSAA_1)));
        double tmpNaDONA_1 = stringToDouble(targetEntity.getNaDONA_1()) - stringToDouble(k1Entity.getNaDONA_1());
        targetEntity.setNaDONA_1(String.valueOf(Math.max(0, tmpNaDONA_1)));
        double tmpPFBA = stringToDouble(targetEntity.getPFBA()) - stringToDouble(k1Entity.getPFBA());
        targetEntity.setPFBA(String.valueOf(Math.max(0, tmpPFBA)));
        double tmpPFBS_1 = stringToDouble(targetEntity.getPFBS_1()) - stringToDouble(k1Entity.getPFBS_1());
        targetEntity.setPFBS_1(String.valueOf(Math.max(0, tmpPFBS_1)));
        double tmpPFDA_1 = stringToDouble(targetEntity.getPFDA_1()) - stringToDouble(k1Entity.getPFDA_1());
        targetEntity.setPFDA_1(String.valueOf(Math.max(0, tmpPFDA_1)));
        double tmpPFDoA_1 = stringToDouble(targetEntity.getPFDoA_1()) - stringToDouble(k1Entity.getPFDoA_1());
        targetEntity.setPFDoA_1(String.valueOf(Math.max(0, tmpPFDoA_1)));
        double tmpPFDS_1 = stringToDouble(targetEntity.getPFDS_1()) - stringToDouble(k1Entity.getPFDS_1());
        targetEntity.setPFDS_1(String.valueOf(Math.max(0, tmpPFDS_1)));
        double tmpPFHpA_1 = stringToDouble(targetEntity.getPFHpA_1()) - stringToDouble(k1Entity.getPFHpA_1());
        targetEntity.setPFHpA_1(String.valueOf(Math.max(0, tmpPFHpA_1)));
        double tmpPFHpS_1 = stringToDouble(targetEntity.getPFHpS_1()) - stringToDouble(k1Entity.getPFHpS_1());
        targetEntity.setPFHpS_1(String.valueOf(Math.max(0, tmpPFHpS_1)));
        double tmpPFHxA_1 = stringToDouble(targetEntity.getPFHxA_1()) - stringToDouble(k1Entity.getPFHxA_1());
        targetEntity.setPFHxA_1(String.valueOf(Math.max(0, tmpPFHxA_1)));
        double tmpPFHxS_11 = (stringToDouble(targetEntity.getPFHxS_11()) - stringToDouble(k1Entity.getPFHxS_11())) * 0.811;
        targetEntity.setPFHxS_11(String.valueOf(Math.max(0, tmpPFHxS_11)));
        double tmpPFNA_1 = stringToDouble(targetEntity.getPFNA_1()) - stringToDouble(k1Entity.getPFNA_1());
        targetEntity.setPFNA_1(String.valueOf(Math.max(0, tmpPFNA_1)));
        double tmpPFNS_1 = stringToDouble(targetEntity.getPFNS_1()) - stringToDouble(k1Entity.getPFNS_1());
        targetEntity.setPFNS_1(String.valueOf(Math.max(0, tmpPFNS_1)));
        double tmpPFOA_1 = stringToDouble(targetEntity.getPFOA_1()) - stringToDouble(k1Entity.getPFOA_1());
        targetEntity.setPFOA_1(String.valueOf(Math.max(0, tmpPFOA_1)));
        double tmpPFOS_1 = (stringToDouble(targetEntity.getPFOS_1()) - stringToDouble(k1Entity.getPFOS_1())) * 0.788;
        targetEntity.setPFOS_1(String.valueOf(Math.max(0, tmpPFOS_1)));
        double tmpPFPeA_1 = stringToDouble(targetEntity.getPFPeA_1()) - stringToDouble(k1Entity.getPFPeA_1());
        targetEntity.setPFPeA_1(String.valueOf(Math.max(0, tmpPFPeA_1)));
        double tmpPFPeS_1 = stringToDouble(targetEntity.getPFPeS_1()) - stringToDouble(k1Entity.getPFPeS_1());
        targetEntity.setPFPeS_1(String.valueOf(Math.max(0, tmpPFPeS_1)));
        double tmpPFTeDA_1 = stringToDouble(targetEntity.getPFTeDA_1()) - stringToDouble(k1Entity.getPFTeDA_1());
        targetEntity.setPFTeDA_1(String.valueOf(Math.max(0, tmpPFTeDA_1)));
        double tmpPFTrDA_1 = (stringToDouble(targetEntity.getPFTrDA_1()) - stringToDouble(k1Entity.getPFTrDA_1())) * 0.5;
        targetEntity.setPFTrDA_1(String.valueOf(Math.max(0, tmpPFTrDA_1)));
        double tmpPFUdA_1 = stringToDouble(targetEntity.getPFUdA_1()) - stringToDouble(k1Entity.getPFUdA_1());
        targetEntity.setPFUdA_1(String.valueOf(Math.max(0, tmpPFUdA_1)));
        double tmpPFOS_21 = (stringToDouble(targetEntity.getPFOS_21()) - stringToDouble(k1Entity.getPFOS_21())) * 0.1;
        targetEntity.setPFOS_21(String.valueOf(Math.max(0, tmpPFOS_21)));
        double tmpPFOS_31 = (stringToDouble(targetEntity.getPFOS_31()) - stringToDouble(k1Entity.getPFOS_31())) * 0.086;
        targetEntity.setPFOS_31(String.valueOf(Math.max(0, tmpPFOS_31)));
        double tmpPFOS_5 = (stringToDouble(targetEntity.getPFOS_5()) - stringToDouble(k1Entity.getPFOS_5())) * 0.012;
        targetEntity.setPFOS_5(String.valueOf(Math.max(0, tmpPFOS_5)));
        double tmpPFHxS_21 = (stringToDouble(targetEntity.getPFHxS_21()) - stringToDouble(k1Entity.getPFHxS_21())) * 0.189;
        targetEntity.setPFHxS_21(String.valueOf(Math.max(0, tmpPFHxS_21)));
    }

    private static boolean containSampleNameResultObj(List<ResultEntity> resultEntityList1, LxnEntity lxnEntity) {
        boolean isExist = false;
        for (ResultEntity resultEntity : resultEntityList1) {
            if (resultEntity.getSampleName().equals(lxnEntity.getSampleName())) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    private static ResultEntity getResultEntity(List<ResultEntity> resultEntityList1, String sampleName) {
        for (ResultEntity resultEntity : resultEntityList1) {
            if (sampleName.equals(resultEntity.getSampleName())) {
                return resultEntity;
            }
        }
        return null;
    }

    private static boolean isInSampleNameBlackList(LxnEntity lxnEntity) {
        boolean isInBlack = false;
        for (String s : Const.SampleNameBlackList) {
            if (lxnEntity.getSampleName().startsWith(s)) {
                isInBlack = true;
                break;
            }
        }
        return isInBlack;
    }

    private static boolean isInSampleNameWhiteList(LxnEntity lxnEntity) {
        boolean isInWhite = false;
        for (String s : Const.SampleNameWhiteList) {
            if (lxnEntity.getSampleName().startsWith(s)) {
                isInWhite = true;
                break;
            }
        }
        return isInWhite;
    }

    private static double stringToDouble(String cacl) {
        try {
            double value = Double.parseDouble(cacl);
            return value;
        } catch (Exception e) {
            return 0;
        }

    }


}
