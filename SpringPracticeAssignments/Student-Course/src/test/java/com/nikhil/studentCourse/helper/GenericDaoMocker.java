package com.nikhil.studentCourse.helper;

import com.nikhil.studentCourse.daos.daoInterfaces.GenericDAO;
import com.nikhil.studentCourse.model.Model;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;

public class GenericDaoMocker<T extends Model>
{
    private Map<Long,T> localStorage;
    public GenericDaoMocker(GenericDAO obj)
    {
        localStorage = new HashMap<>();
        Mockito.when(obj.list()).thenAnswer(invocation ->
        {
            List<T> retVal = new ArrayList<>(localStorage.values());
            return retVal;
        });
        Mockito.when(obj.save(any())).thenAnswer(
                invocation -> {
                    T param = invocation.getArgument(0);
                    localStorage.put(param.getId(),param);
                    return param;
                }
        );
        Mockito.when(obj.remove(any())).thenAnswer(
                invocation -> {
                    long id = invocation.getArgument(0);
                    if(localStorage.containsKey(id))
                    {
                        localStorage.remove(id);
                        return true;
                    }
                    return false;
                }
        );
        Mockito.when(obj.find(any())).thenAnswer(
                invocation -> {
                    long id = invocation.getArgument(0);
                    Optional<T> retVal = Optional.ofNullable(localStorage.get(id));
                    return retVal;
                }
        );

    }
}