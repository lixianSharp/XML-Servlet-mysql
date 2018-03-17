package gz.itcast.e_annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import java.lang.annotation.ElementType;
/**
 * ע��
 * @author APPle
 *
 */
/**
 * @Taget�������Ƕ���ע���ʹ�÷�Χ
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {

	//��������
	String name();
	String modifyTime() default "2015-06-25";//�����Դ���Ĭ��ֵ
	String[] address();//�����������͵�����
	//���ע�����������Ϊvalue����ô��ʹ��ע���ʱ����Բ�дvalue=
	String[] value();
	//String[] names();
	//String value();
}
