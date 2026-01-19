package software.xdev;

import java.util.stream.Stream;

import software.xdev.dynamicreports.jasper.builder.export.Exporters;
import software.xdev.dynamicreports.report.builder.DynamicReports;
import software.xdev.dynamicreports.report.builder.column.ColumnBuilders;
import software.xdev.dynamicreports.report.exception.DRException;


public final class Application
{
	private Application()
	{
	}
	
	@SuppressWarnings("java:S106") // Just a simple demo
	public static void main(final String[] args) throws DRException
	{
		DynamicReports.report()
			.setDataSource(Stream.of("a", "b", "c")
				.map(DummyDTO::new)
				.toList())
			.columns(new ColumnBuilders()
				.column("value", String.class))
			.export(Exporters.csvExporter(System.out));
	}
	
	public record DummyDTO(String value)
	{
		public String getValue()
		{
			return this.value();
		}
	}
}
