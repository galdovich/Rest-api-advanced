package com.galdovich.esm.controller;

import com.galdovich.esm.dto.PageDTO;
import com.galdovich.esm.dto.TagDTO;
import com.galdovich.esm.service.TagService;
import com.galdovich.esm.util.HateoasData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Class {@code TagController} is an endpoint of the API
 * which allows to perform CRUD operations on tags.
 * Annotated by {@link RestController} with no parameters to provide an answer in application/json.
 * Annotated by {@link RequestMapping} with parameter value = "/tags".
 * So that {@code TagController} is accessed by sending request to /tags.
 */
@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    /**
     * Instantiates a new Tag controller.
     *
     * @param tagService the tag service
     */
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Find all tags.
     * Annotated by {@link GetMapping}.
     * Therefore, processes GET requests at /tags.
     *
     * @param page the current page
     * @param size amount of tags on the page
     * @return the list of found certificates
     */
    @GetMapping
    public List<TagDTO> getAll(@RequestParam(required = false, defaultValue = "1") int page,
                               @RequestParam(required = false, defaultValue = "5") int size) {
        PageDTO pageDTO = new PageDTO(page, size);
        List<TagDTO> foundTags = tagService.getAll(pageDTO);
        foundTags.forEach(this::addLinks);
        return foundTags;
    }

    /**
     * Get tag by id.
     * Annotated by {@link GetMapping} with parameter value = "/{id}".
     * Therefore, processes GET requests at /tags/{id}.
     *
     * @param id the tag id that will be found. Inferred from the request URI
     * @return the found tag
     */
    @GetMapping("/{id}")
    public TagDTO getById(@PathVariable("id") long id) {
        TagDTO foundTag = tagService.getById(id);
        addLinks(foundTag);
        return foundTag;
    }

    /**
     * Add new tag.
     * Annotated by {@link PostMapping} with no parameters.
     * Therefore, processes POST requests at /tags.
     *
     * @param tagDTO the new tag that will be added
     * @return the added tag
     */
    @PostMapping
    public TagDTO add(@RequestBody TagDTO tagDTO) {
        TagDTO addedTag = tagService.add(tagDTO);
        addLinks(addedTag);
        return addedTag;
    }

    /**
     * Delete tag with the specified id.
     * Annotated by {@link DeleteMapping} with parameter value = "/{id}".
     * Therefore, processes DELETE requests at /tags/{id}.
     *
     * @param id the tag id which will be deleted. Inferred from the request URI
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        tagService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Get widely used tag.
     * Annotated by {@link GetMapping} with no parameter.
     * Therefore, processes GET requests at /tags/widely-used.
     *
     * @return the widely used tag
     */
    @GetMapping("/widely-used")
    public TagDTO getWidelyUsedTag() {
        TagDTO foundTag = tagService.getMostPopularUserTagWithHighestOrderSum();
        addLinks(foundTag);
        return foundTag;
    }

    private void addLinks(TagDTO tagDTO) {
        tagDTO.add(linkTo(methodOn(TagController.class).getById(tagDTO.getId()))
                .withSelfRel());
        tagDTO.add(linkTo(methodOn(TagController.class).delete(tagDTO.getId()))
                .withRel(HateoasData.DELETE));
    }
}
