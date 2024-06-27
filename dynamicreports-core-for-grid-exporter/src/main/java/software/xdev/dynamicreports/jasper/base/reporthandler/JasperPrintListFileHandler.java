/*
 * dynamicreports-core-for-grid-exporter - dynamicreports-core-for-grid-exporter
 * Copyright © 2023 XDEV Software (https://xdev.software)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package software.xdev.dynamicreports.jasper.base.reporthandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRVirtualizer;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import software.xdev.dynamicreports.report.exception.DRReportException;


public class JasperPrintListFileHandler extends AbstractPrintListHandler
{
	private static final String TEMP_FILE_PREFIX = "JasperPrint";
	
	private final List<JasperPrint> printList;
	private final List<File> tempFiles;
	private File directory;
	private final JRVirtualizer virtualizer;
	
	public JasperPrintListFileHandler(final String directory)
	{
		this(directory, null);
	}
	
	public JasperPrintListFileHandler(final String directory, final JRVirtualizer virtualizer)
	{
		this.virtualizer = virtualizer;
		if(directory != null)
		{
			this.directory = new File(directory);
		}
		this.printList = new PrintList();
		this.tempFiles = new ArrayList<>();
	}
	
	@Override
	protected void add(final JasperPrint jasperPrint)
	{
		try
		{
			final File tempFile = File.createTempFile(TEMP_FILE_PREFIX, null, this.directory);
			JRSaver.saveObject(jasperPrint, tempFile);
			this.tempFiles.add(tempFile);
		}
		catch(final JRException e)
		{
			throw new DRReportException(e);
		}
		catch(final IOException e)
		{
			throw new DRReportException(e);
		}
	}
	
	@Override
	public List<JasperPrint> getPrintList()
	{
		return this.printList;
	}
	
	@SuppressWarnings("checkstyle:NoFinalizer")
	@Override
	protected void finalize() throws Throwable
	{
		for(final File tempFile : this.tempFiles)
		{
			tempFile.delete();
		}
		super.finalize();
	}
	
	class PrintList implements List<JasperPrint>
	{
		@Override
		public int size()
		{
			return JasperPrintListFileHandler.this.tempFiles.size();
		}
		
		@Override
		public boolean isEmpty()
		{
			return JasperPrintListFileHandler.this.tempFiles.isEmpty();
		}
		
		@Override
		public boolean contains(final Object o)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public Iterator<JasperPrint> iterator()
		{
			return new Iterator<>()
			{
				private final Iterator<File> it = JasperPrintListFileHandler.this.tempFiles.iterator();
				
				@Override
				public boolean hasNext()
				{
					return this.it.hasNext();
				}
				
				@Override
				public JasperPrint next()
				{
					final File tempFile = this.it.next();
					try
					{
						final JasperPrint jasperPrint = JRLoader.loadJasperPrint(
							tempFile,
							JasperPrintListFileHandler.this.virtualizer);
						return jasperPrint;
					}
					catch(final JRException e)
					{
						throw new DRReportException(e);
					}
				}
				
				@Override
				public void remove()
				{
					throw new UnsupportedOperationException();
				}
			};
		}
		
		@Override
		public Object[] toArray()
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public <T> T[] toArray(final T[] a)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public boolean add(final JasperPrint o)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public boolean remove(final Object o)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public boolean containsAll(final Collection<?> c)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public boolean addAll(final Collection<? extends JasperPrint> c)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public boolean addAll(final int index, final Collection<? extends JasperPrint> c)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public boolean removeAll(final Collection<?> c)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public boolean retainAll(final Collection<?> c)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public void clear()
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public JasperPrint get(final int index)
		{
			try
			{
				final File tempFile = JasperPrintListFileHandler.this.tempFiles.get(index);
				final JasperPrint jasperPrint = JRLoader.loadJasperPrint(
					tempFile,
					JasperPrintListFileHandler.this.virtualizer);
				return jasperPrint;
			}
			catch(final JRException e)
			{
				throw new DRReportException(e);
			}
		}
		
		@Override
		public JasperPrint set(final int index, final JasperPrint element)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public void add(final int index, final JasperPrint element)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public JasperPrint remove(final int index)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public int indexOf(final Object o)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public int lastIndexOf(final Object o)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public ListIterator<JasperPrint> listIterator()
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public ListIterator<JasperPrint> listIterator(final int index)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public List<JasperPrint> subList(final int fromIndex, final int toIndex)
		{
			throw new UnsupportedOperationException();
		}
	}
}
