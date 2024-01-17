package com.bangbang.domain.item;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;
    QItem qItem = QItem.item;

    public List<Item> findItemLimit20(Long id, Integer size) {
        List<Item> itemList = jpaQueryFactory
                .selectFrom(qItem)
                .join(qItem.option).fetchJoin()
                .join(qItem.itemPrice).fetchJoin()
                .join(qItem.manageOption).fetchJoin()
                .where(qItem.item_id.lt(id))
                .orderBy(qItem.item_id.desc())
                .limit(size+1)
                .fetch();

        return itemList;
    }
}
