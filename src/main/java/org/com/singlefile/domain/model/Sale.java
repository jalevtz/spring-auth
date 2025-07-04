package org.com.singlefile.domain.model;

import com.querydsl.core.annotations.QueryEmbedded;
import com.querydsl.core.annotations.QueryEntity;
import com.querydsl.core.annotations.QueryInit;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


import java.beans.BeanProperty;
import java.beans.Transient;
import java.util.Date;
import java.util.List;

@Document("sales")
public record Sale (@Id String id, Date saleDate, String storeLocation,
                    boolean couponUsed, String purchaseMethod) {

}
