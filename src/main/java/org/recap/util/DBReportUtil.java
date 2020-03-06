package org.recap.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.marc4j.marc.Record;
import org.recap.ReCAPConstants;
import org.recap.RecapCommonConstants;
import org.recap.gfa.model.*;
import org.recap.model.BibliographicEntity;
import org.recap.model.HoldingsEntity;
import org.recap.model.ItemEntity;
import org.recap.model.jpa.*;
import org.recap.model.jpa.ReportDataEntity;
import org.recap.repository.CollectionGroupDetailsRepository;
import org.recap.repository.InstitutionDetailsRepository;
import org.recap.repository.ItemStatusDetailsRepository;
import org.recap.request.GFAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chenchulakshmig on 17/10/16.
 */
@Service
public class DBReportUtil {

    private Map<String, Integer> institutionEntitiesMap;
    private Map<String, Integer> collectionGroupMap;
    private Map institutionEntityMap;

    @Autowired
    private InstitutionDetailsRepository institutionDetailsRepository;

    @Autowired
    private CollectionGroupDetailsRepository collectionGroupDetailsRepository;

    /**
     * The Gfa service.
     */
    @Autowired
    GFAService gfaService;

    /**
     * Gets institution entities map.
     *
     * @return the institution entities map
     */
    public Map<String, Integer> getInstitutionEntitiesMap() {
        return institutionEntitiesMap;
    }

    /**
     * Sets institution entities map.
     *
     * @param institutionEntitiesMap the institution entities map
     */
    public void setInstitutionEntitiesMap(Map<String, Integer> institutionEntitiesMap) {
        this.institutionEntitiesMap = institutionEntitiesMap;
    }

    /**
     * Gets collection group map.
     *
     * @return the collection group map
     */
    public Map<String, Integer> getCollectionGroupMap() {
        return collectionGroupMap;
    }

    /**
     * Sets collection group map.
     *
     * @param collectionGroupMap the collection group map
     */
    public void setCollectionGroupMap(Map<String, Integer> collectionGroupMap) {
        this.collectionGroupMap = collectionGroupMap;
    }

    /**
     * Generate bib failure report entity list.
     *
     * @param bibliographicEntity the bibliographic entity
     * @param record              the record
     * @return the list
     */
    public List<ReportDataEntity> generateBibFailureReportEntity(BibliographicEntity bibliographicEntity, Record record) {
        List<ReportDataEntity> reportDataEntities = new ArrayList<>();
        ReportDataEntity owningInstitutionReportDataEntity = new ReportDataEntity();

        if (bibliographicEntity.getOwningInstitutionId() != null) {
            for (Map.Entry<String, Integer> entry : institutionEntitiesMap.entrySet()) {
                if (entry.getValue() == bibliographicEntity.getOwningInstitutionId()) {
                    owningInstitutionReportDataEntity.setHeaderName(RecapCommonConstants.OWNING_INSTITUTION);
                    owningInstitutionReportDataEntity.setHeaderValue(entry.getKey());
                    reportDataEntities.add(owningInstitutionReportDataEntity);
                    break;
                }
            }
        }

        if (StringUtils.isNotBlank(bibliographicEntity.getOwningInstitutionBibId())) {
            ReportDataEntity owningInstitutionBibIdReportDataEntity = new ReportDataEntity();
            owningInstitutionBibIdReportDataEntity.setHeaderName(RecapCommonConstants.OWNING_INSTITUTION_BIB_ID);
            owningInstitutionBibIdReportDataEntity.setHeaderValue(bibliographicEntity.getOwningInstitutionBibId());
            reportDataEntities.add(owningInstitutionBibIdReportDataEntity);
        }

        String title = new MarcUtil().getDataFieldValue(record, "245", 'a');
        if (StringUtils.isNotBlank(title)) {
            ReportDataEntity titleReportDataEntity = new ReportDataEntity();
            titleReportDataEntity.setHeaderName(RecapCommonConstants.TITLE);
            titleReportDataEntity.setHeaderValue(title.trim());
            reportDataEntities.add(titleReportDataEntity);
        }
        return reportDataEntities;
    }

    /**
     * Generate bib holdings failure report entity list.
     *
     * @param bibliographicEntity the bibliographic entity
     * @param holdingsEntity      the holdings entity
     * @param institutionName     the institution name
     * @param bibRecord           the bib record
     * @return the list
     */
    public List<ReportDataEntity> generateBibHoldingsFailureReportEntity(BibliographicEntity bibliographicEntity, HoldingsEntity holdingsEntity, String institutionName, Record bibRecord) {
        List<ReportDataEntity> reportDataEntities = new ArrayList<>();
        reportDataEntities.addAll(generateBibFailureReportEntity(bibliographicEntity, bibRecord));
        if (holdingsEntity != null) {
                ReportDataEntity owningInstitutionHoldingsIdReportDataEntity = new ReportDataEntity();
                owningInstitutionHoldingsIdReportDataEntity.setHeaderName(RecapCommonConstants.OWNING_INSTITUTION_HOLDINGS_ID);
                owningInstitutionHoldingsIdReportDataEntity.setHeaderValue(holdingsEntity.getOwningInstitutionHoldingsId());
                reportDataEntities.add(owningInstitutionHoldingsIdReportDataEntity);
            }
        return reportDataEntities;
    }

    /**
     * Generate bib holdings and items failure report entities list.
     *
     * @param bibliographicEntity the bibliographic entity
     * @param holdingsEntity      the holdings entity
     * @param itemEntity          the item entity
     * @param institutionName     the institution name
     * @param bibRecord           the bib record
     * @return the list
     */
    public List<ReportDataEntity> generateBibHoldingsAndItemsFailureReportEntities(BibliographicEntity bibliographicEntity, HoldingsEntity holdingsEntity, ItemEntity itemEntity, String institutionName, Record bibRecord) {
        List<ReportDataEntity> reportEntities = new ArrayList<>();
        reportEntities.addAll(generateBibHoldingsFailureReportEntity(bibliographicEntity, holdingsEntity, institutionName, bibRecord));

        if (itemEntity != null) {
            if (StringUtils.isNotBlank(itemEntity.getOwningInstitutionItemId())) {
                ReportDataEntity localItemIdReportDataEntity = new ReportDataEntity();
                localItemIdReportDataEntity.setHeaderName(RecapCommonConstants.LOCAL_ITEM_ID);
                localItemIdReportDataEntity.setHeaderValue(itemEntity.getOwningInstitutionItemId());
                reportEntities.add(localItemIdReportDataEntity);
            }

            if (StringUtils.isNotBlank(itemEntity.getBarcode())) {
                ReportDataEntity itemBarcodeReportDataEntity = new ReportDataEntity();
                itemBarcodeReportDataEntity.setHeaderName(RecapCommonConstants.ITEM_BARCODE);
                itemBarcodeReportDataEntity.setHeaderValue(itemEntity.getBarcode());
                reportEntities.add(itemBarcodeReportDataEntity);
            }

            if (StringUtils.isNotBlank(itemEntity.getCustomerCode())) {
                ReportDataEntity customerCodeReportDataEntity = new ReportDataEntity();
                customerCodeReportDataEntity.setHeaderName(RecapCommonConstants.CUSTOMER_CODE);
                customerCodeReportDataEntity.setHeaderValue(itemEntity.getCustomerCode());
                reportEntities.add(customerCodeReportDataEntity);
            }

            if (itemEntity.getCollectionGroupId() != null) {
                for (Map.Entry<String, Integer> entry : collectionGroupMap.entrySet()) {
                    if (entry.getValue() == itemEntity.getCollectionGroupId()) {
                        ReportDataEntity collectionGroupDesignationEntity = new ReportDataEntity();
                        collectionGroupDesignationEntity.setHeaderName(RecapCommonConstants.COLLECTION_GROUP_DESIGNATION);
                        collectionGroupDesignationEntity.setHeaderValue(entry.getKey());
                        reportEntities.add(collectionGroupDesignationEntity);
                        break;
                    }
                }
            }

            if (itemEntity.getCreatedDate() != null) {
                ReportDataEntity createDateItemEntity = new ReportDataEntity();
                createDateItemEntity.setHeaderName(RecapCommonConstants.CREATE_DATE_ITEM);
                createDateItemEntity.setHeaderValue(new SimpleDateFormat("mm-dd-yyyy").format(itemEntity.getCreatedDate()));
                reportEntities.add(createDateItemEntity);
            }

            if (itemEntity.getLastUpdatedDate() != null) {
                ReportDataEntity lastUpdateItemEntity = new ReportDataEntity();
                lastUpdateItemEntity.setHeaderName(RecapCommonConstants.LAST_UPDATED_DATE_ITEM);
                lastUpdateItemEntity.setHeaderValue(new SimpleDateFormat("mm-dd-yyyy").format(itemEntity.getLastUpdatedDate()));
                reportEntities.add(lastUpdateItemEntity);
            }

        }
        return reportEntities;
    }

    /**
     * Gets institution entity map.
     *
     * @return the institution entity map
     */
    public Map getInstitutionEntityMap() {
        if (null == institutionEntityMap) {
            institutionEntityMap = new HashMap();
            try {
                Iterable<InstitutionEntity> institutionEntities = institutionDetailsRepository.findAll();
                for (Iterator iterator = institutionEntities.iterator(); iterator.hasNext(); ) {
                    InstitutionEntity institutionEntity = (InstitutionEntity) iterator.next();
                    institutionEntityMap.put(institutionEntity.getInstitutionCode(), institutionEntity.getInstitutionId());
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return institutionEntityMap;
    }

    public Map getCollectionGroupsMap() {
        if (null == collectionGroupMap) {
            collectionGroupMap = new HashMap();
            try {
                Iterable<CollectionGroupEntity> collectionGroupEntities = collectionGroupDetailsRepository.findAll();
                for (Iterator iterator = collectionGroupEntities.iterator(); iterator.hasNext(); ) {
                    CollectionGroupEntity collectionGroupEntity = (CollectionGroupEntity) iterator.next();
                    collectionGroupMap.put(collectionGroupEntity.getCollectionGroupCode(), collectionGroupEntity.getCollectionGroupId());
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return collectionGroupMap;
    }

    public String callGfaItemStatus(String itemBarcode) {
        String gfaItemStatusValue = null;
        GFAItemStatusCheckRequest gfaItemStatusCheckRequest = new GFAItemStatusCheckRequest();
        GFAItemStatus gfaItemStatus = new GFAItemStatus();
        gfaItemStatus.setItemBarCode(itemBarcode);
        gfaItemStatusCheckRequest.setItemStatus(Arrays.asList(gfaItemStatus));
        GFAItemStatusCheckResponse gfaItemStatusCheckResponse = gfaService.itemStatusCheck(gfaItemStatusCheckRequest);
        if (null != gfaItemStatusCheckResponse) {
            Dsitem dsitem = gfaItemStatusCheckResponse.getDsitem();
            if (null != dsitem) {
                List<Ttitem> ttitems = dsitem.getTtitem();
                if (CollectionUtils.isNotEmpty(ttitems)) {
                    gfaItemStatusValue = ttitems.get(0).getItemStatus();
                }
            }
        }
        return gfaItemStatusValue;
    }

}
