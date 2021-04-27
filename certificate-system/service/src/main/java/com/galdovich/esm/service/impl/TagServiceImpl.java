package com.galdovich.esm.service.impl;

import com.galdovich.esm.dao.TagDAO;
import com.galdovich.esm.dao.UserDAO;
import com.galdovich.esm.dto.PageDTO;
import com.galdovich.esm.dto.TagDTO;
import com.galdovich.esm.entity.Tag;
import com.galdovich.esm.exception.AlreadyExistsException;
import com.galdovich.esm.exception.MessageKey;
import com.galdovich.esm.exception.ResourcesNotFoundException;
import com.galdovich.esm.exception.UnsupportedMethodException;
import com.galdovich.esm.service.TagService;
import com.galdovich.esm.util.GiftConverter;
import com.galdovich.esm.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagDAO tagDAO;
    private final UserDAO userDAO;

    @Autowired
    public TagServiceImpl(TagDAO tagDAO, UserDAO userDAO) {
        this.tagDAO = tagDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<TagDTO> getAll(PageDTO pageDTO) {
        Page page = GiftConverter.toPage(pageDTO);
        List<Tag> list = tagDAO.getAll(page);
        return GiftConverter.toTagDTOList(list);
    }

    @Override
    public TagDTO getById(long id) throws ResourcesNotFoundException {
        Optional<Tag> tag = tagDAO.getById(id);
        return tag.map(GiftConverter::toTagDTO)
                .orElseThrow(() -> new ResourcesNotFoundException(MessageKey.TAG_NOT_FOUND_BY_ID,
                        String.valueOf(id)));
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        getById(id);
        tagDAO.deleteCertificateHasTag(id);
        return tagDAO.delete(id);
    }

    @Transactional
    @Override
    public TagDTO add(TagDTO tagDTO) throws AlreadyExistsException {
        Tag tag = GiftConverter.toTag(tagDTO);
        if (isExists(tag.getName())) {
            throw new AlreadyExistsException(MessageKey.TAG_ALREADY_EXISTS, tag.getName());
        }
        Tag updated = tag.getId() != null ? tagDAO.edit(tag) : tagDAO.add(tag);
        return GiftConverter.toTagDTO(updated);
    }

    @Override
    public TagDTO update(long id, TagDTO tagDTO) throws UnsupportedMethodException {
        throw new UnsupportedMethodException(MessageKey.UNSUPPORTED_METHOD);
    }

    @Override
    public boolean isExists(String name) {
        Optional<Tag> tag = tagDAO.getByName(name);
        return tag.isPresent();
    }

    @Transactional
    @Override
    public TagDTO getMostPopularUserTagWithHighestOrderSum() throws ResourcesNotFoundException {
        long userId = userDAO.getByHighestCostOfAllOrders();
        long tagId = tagDAO.getMostPopularUserTag(userId);
        return getById(tagId);
    }

    @Override
    public TagDTO getByName(String name) throws ResourcesNotFoundException {
        Optional<Tag> tag = tagDAO.getByName(name);
        if (tag.isEmpty()) {
            throw new ResourcesNotFoundException(MessageKey.TAG_NOT_FOUND_BY_NAME, name);
        }
        return tag.map(GiftConverter::toTagDTO).get();
    }
}
