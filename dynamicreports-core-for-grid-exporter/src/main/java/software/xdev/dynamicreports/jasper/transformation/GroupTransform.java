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
package software.xdev.dynamicreports.jasper.transformation;

import software.xdev.dynamicreports.design.definition.DRIDesignGroup;
import software.xdev.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignGroup;

/**
 * <p>GroupTransform class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class GroupTransform {
    private JasperTransformAccessor accessor;

    /**
     * <p>Constructor for GroupTransform.</p>
     *
     * @param accessor a {@link software.xdev.dynamicreports.jasper.transformation.JasperTransformAccessor} object.
     */
    public GroupTransform(JasperTransformAccessor accessor) {
        this.accessor = accessor;
    }

    /**
     * <p>transform.</p>
     */
    public void transform() {
        for (DRIDesignGroup group : accessor.getReport().getGroups()) {
            addGroup(group);
        }
    }

    private void addGroup(DRIDesignGroup group) {
        try {
            JRDesignGroup jrGroup = group(group);
            accessor.getDesign().addGroup(jrGroup);
        } catch (JRException e) {
            throw new JasperDesignException("Registration failed for group \"" + group.getName() + "\"", e);
        }
    }

    private JRDesignGroup group(DRIDesignGroup group) {
        JRDesignGroup jrGroup = new JRDesignGroup();
        jrGroup.setName(group.getName());
        jrGroup.setReprintHeaderOnEachPage(group.isReprintHeaderOnEachPage());
        jrGroup.setStartNewColumn(group.isStartInNewColumn());
        jrGroup.setStartNewPage(group.isStartInNewPage());
        jrGroup.setResetPageNumber(group.isResetPageNumber());
        if (group.getMinHeightToStartNewPage() != null) {
            jrGroup.setMinHeightToStartNewPage(group.getMinHeightToStartNewPage());
        }
        jrGroup.setFooterPosition(ConstantTransform.groupFooterPosition(group.getFooterPosition()));
        jrGroup.setKeepTogether(group.isKeepTogether());
        return jrGroup;
    }

    /**
     * <p>transformExpressions.</p>
     */
    public void transformExpressions() {
        for (DRIDesignGroup group : accessor.getReport().getGroups()) {
            getGroup(group).setExpression(accessor.getExpressionTransform().getExpression(group.getGroupExpression()));
        }
    }

    /**
     * <p>getGroup.</p>
     *
     * @param group a {@link software.xdev.dynamicreports.design.definition.DRIDesignGroup} object.
     * @return a {@link net.sf.jasperreports.engine.design.JRDesignGroup} object.
     */
    public JRDesignGroup getGroup(DRIDesignGroup group) {
        JRDesignGroup jrGroup = (JRDesignGroup) accessor.getDesign().getGroupsMap().get(group.getName());
        if (jrGroup == null) {
            throw new JasperDesignException("Group " + group.getName() + " is not registered");
        }
        return jrGroup;
    }
}
