package de.bitnoise.sonferenz.service.v2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TestClass
{
//  @Test
//  public void test()
//  {
////    Dummy target = spy(new Dummy());
//    Dummy target = mock(Dummy.class);
//
//    when(target.getAValue()).thenReturn("Magic1");
//    verifyNoMoreInteractions(target);
//
//    System.out.println(target.getAValue());
//    System.out.println(target.getAValue());
//
//    verify(target);
//  }

  interface IToMock
  {
    String getAValue();
  }

  class Dummy implements IToMock
  {

    @Override
    public String getAValue()
    {
      return null;
    }
  }
}
