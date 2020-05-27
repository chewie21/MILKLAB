package com.example.milk.service;

import com.example.milk.domain.Group;
import com.example.milk.repos.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private ProductGroupService productGroupService;

    //Find
    public List<Group> findAllGroup() {
        return groupRepo.findAll();
    }
    public Group findById (Long groupId) {
        return groupRepo.findById(groupId).get();
    }

    //Edit
    public void SaveGroup(String prodGroup) {
        Group group = new Group(prodGroup);
        groupRepo.save(group);
    }
    public void deleteGroup (Group group) {
        productGroupService.deleteGroup(group.getId());
        groupRepo.delete(group);
    }
}
