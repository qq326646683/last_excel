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
        return "xxx";
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
        for (LxnEntity lxnEntity : cachedDataList) {
            //表1: 结果列表ResultEntity里无该SampleName对象，且不在SampleName黑名单里，就创建ResultEntity对象
            if (!containSampleNameResultObj(resultEntityList1, lxnEntity) && !isInSampleNameBlackList(lxnEntity) && Const.SampleType.equals(lxnEntity.getSampleType())) {
                ResultEntity resultEntity = new ResultEntity();
                resultEntity.setSampleName(lxnEntity.getSampleName());
                resultEntityList1.add(resultEntity);
            }

            ResultEntity resultEntity1 = getResultEntity(resultEntityList1, lxnEntity.getSampleName());
            if (resultEntity1 != null) {
                setComponentName(lxnEntity, resultEntity1);
            }

            //表2: 结果列表ResultEntity里无该SampleName对象，且在SampleName白名单里，就创建ResultEntity对象
            if (!containSampleNameResultObj(resultEntityList2, lxnEntity) && isInSampleNameWhiteList(lxnEntity) && Const.SampleType.equals(lxnEntity.getSampleType())) {
                ResultEntity resultEntity = new ResultEntity();
                resultEntity.setSampleName(lxnEntity.getSampleName());
                resultEntityList2.add(resultEntity);
            }

            ResultEntity resultEntity2 = getResultEntity(resultEntityList2, lxnEntity.getSampleName());
            if (resultEntity2 != null) {
                setComponentName(lxnEntity, resultEntity2);
            }
        }

        try (ExcelWriter excelWriter = EasyExcel.write(savePath).build()) {
            WriteSheet writeSheet1 = EasyExcel.writerSheet(0, "raw").head(ResultEntity.class).build();
            excelWriter.write(resultEntityList1, writeSheet1);
            WriteSheet writeSheet2 = EasyExcel.writerSheet(1, "K&R").head(ResultEntity.class).build();
            excelWriter.write(resultEntityList2, writeSheet2);
        }

    }

    private static void setComponentName(LxnEntity lxnEntity, ResultEntity resultEntity) {
        String calc = lxnEntity.getCalculatedConcentration();
        switch (lxnEntity.getComponentName()) {
            case Const.FTS42_1:
                resultEntity.setFTS42_1(calc);
                break;
            case Const.ClPFESA62_1:
                resultEntity.setClPFESA62_1(calc);
                break;
            case Const.FTS62_1:
                resultEntity.setFTS62_1(calc);
                break;
            case Const.ClPFESA81_1:
                resultEntity.setClPFESA81_1(calc);
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
            case Const.PFOS_2:
                resultEntity.setPFOS_2(calc);
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


}
