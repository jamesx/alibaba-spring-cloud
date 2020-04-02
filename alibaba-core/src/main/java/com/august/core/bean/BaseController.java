package com.august.core.bean;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

public class BaseController<S extends ServiceImpl<M, T>, M extends BaseMapper<T>, T> {
    @Autowired
    protected S service;

    @Autowired
    protected M mapper;

    /**
     * @param params
     * page:当前页
     * limit:每页显示多少个
     * orderField:排序字段
     * order:ASC|DESC
     *
     * @return
     */
    @ApiOperation("分页查询")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        IPage<T> page = service.page(
                new Query<T>().getPage(params),
                new QueryWrapper<>()
        );
        return R.ok().put("page", new PageVo(page));
    }

    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        T t=service.getById(id);
        return R.ok(t);
    }

    @ApiOperation("保存")
    @PostMapping("/save")
    public R save(@RequestBody T t){
        boolean save = service.save(t);
        return save?R.ok():R.error("添加失败");
    }

    @ApiOperation("修改")
    @PostMapping("/update")
    public R update(@RequestBody T t){
        boolean b = service.updateById(t);
        return b?R.ok():R.error("更新失败");
    }

    @ApiOperation("删除")
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        boolean b = service.removeByIds(Arrays.asList(ids));
        return b?R.ok():R.error("删除失败");
    }
}
