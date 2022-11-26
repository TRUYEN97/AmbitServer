/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MOdel;

import java.io.File;
import java.util.List;

/**
 *
 * @author Administrator
 */

    public class ProgramParameter {

        public ProgramParameter() {
        }

        public ProgramParameter setProjectName(String projectName) throws Exception {
            if (projectName == null || projectName.isBlank()) {
                throw new Exception("Project name is empty!");
            }
            this.projectName = projectName;
            return this;
        }

        public ProgramParameter setProgramName(String programName) throws Exception {
            if (programName == null || programName.isBlank()) {
                throw new Exception("Project name is empty!");
            }
            this.programName = programName;
            return this;
        }

        public ProgramParameter setVersion(String version) throws Exception {
            if (version == null || version.isBlank()) {
                throw new Exception("Project version is empty!");
            }
            this.version = version;
            return this;
        }

        public ProgramParameter setDescription(String description, String desOF) throws Exception {
            if (description == null || description.isBlank()) {
                throw new Exception(desOF +" description is empty!");
            }
            this.description = description;
            return this;
        }

        public ProgramParameter setProgramPath(String programPath) throws Exception {
            if (programPath == null || programPath.isBlank()) {
                throw new Exception("Please... add a program");
            }
            this.programPath = programPath;
            return this;
        }

        public ProgramParameter setCommandRun(String commandRun) throws Exception {
            if (commandRun == null || commandRun.isBlank()) {
                throw new Exception("Project command Run.bat is empty!");
            }
            this.commandRun = commandRun;
            return this;
        }

        public ProgramParameter setFolderSource(String folderSource) {
            this.folderSource = folderSource;
            return this;
        }
        
        public ProgramParameter setConfigName(String configName) throws Exception {
            if (configName == null || configName.isBlank()) {
                throw new Exception("config name is empty!");
            }
            this.configName = configName;
            return this;
        }
        
        public ProgramParameter setFolderType(int type) {
            this.type = type;
            return this;
        }

        public ProgramParameter setLists(List<File> lists) {
            this.lists = lists;
            return this;
        }
        
        private int type;
        private String projectName;
        private String configName;
        private String version;
        private String description;
        private String folderSource;
        private String programName;
        private String programPath;
        private String commandRun;
        private List<File> lists;

        public int getType() {
            return type;
        }

        public String getProjectName() {
            return projectName;
        }

        public String getConfigName() {
            return configName;
        }

        public String getVersion() {
            return version;
        }

        public String getDescription() {
            return description;
        }

        public String getFolderSource() {
            return folderSource;
        }

        public String getProgramName() {
            return programName;
        }

        public String getProgramPath() {
            return programPath;
        }

        public String getCommandRun() {
            return commandRun;
        }

        public List<File> getLists() {
            return lists;
        }
        
    }

