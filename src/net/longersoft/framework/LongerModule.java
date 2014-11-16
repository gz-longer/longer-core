package net.longersoft.framework;

import java.util.ArrayList;
import java.util.List;

import net.longersoft.helpers.ReflectionHelper;

import org.apache.log4j.Logger;
public abstract class LongerModule {
	private static Logger log = Logger.getLogger(LongerModule.class);

	protected ServiceSession session;
	public LongerModule() {
		this.session = new ServiceSession().initByUserId("system");
	}
	
	public String getModuleId(){
		return this.getClass().getName().toLowerCase();
	}
	
	public String getModuleName() {
		return this.getClass().getSimpleName();
	}
	
	public void update(boolean updateDependency) throws Exception{
		if(updateDependency){
			LongerModule[] modules = getMeDependentOnModules();
			if(modules != null && modules.length > 0){
				for(int i = 0; i < modules.length; i++){
					modules[i].update(updateDependency);
				}
			}
		}
		
		if(this.isInstalled()){
			log.info(this.getModuleName() + " installed, start to update...");
			this.setInstallStatus("updating");
			this.update();
			this.setInstallStatus("updated");
			log.info(this.getModuleName() + " updated.");
		}else{
			log.info(this.getModuleName() + " not installed, ignore it");
		}
	}
	
	public void install(boolean installDependency) throws Exception{
		if(installDependency){
			LongerModule[] modules = getMeDependentOnModules();
			if(modules != null && modules.length > 0){
				for(int i = 0; i < modules.length; i++){
					modules[i].install(installDependency);
				}
			}
		}
		
		if(!this.isInstalled()){
			log.info(this.getModuleName() + " uninstalled, start to install...");
			this.setInstallStatus("installing");
			this.install();
			this.init();
			this.setInstallStatus("installed");
			log.info(this.getModuleName() + " installed.");
		}else{
			log.info(this.getModuleName() + " installed, ignore it");
		}
	}
	
	public void uninstall(boolean uninstallDependency) throws Exception{
		if(uninstallDependency){
			LongerModule[] modules = getDependentOnMeModules();
			if(modules != null && modules.length > 0){
				for(int i = 0; i<modules.length; i++){
					modules[i].uninstall(uninstallDependency);
				}
			}
		}
		if(this.isInstalled()){
			log.info(this.getModuleName() + " installed, start to uninstall...");
			this.setInstallStatus("uninstalling");
			this.uninstall();
			this.setInstallStatus("uninstalled");
			log.info(this.getModuleName() + " uninstalled.");
		}else{
			log.info(this.getModuleName() + " not installed, ignore it");
		}
	}
	
	protected void setInstallStatus(String status) throws Exception{
		log.info(String.format("%s %s.", this.getModuleName(), status));
	}
	
	public LongerModule[] getMeDependentOnModules() throws Exception{
		DependentModule dependentModule = (DependentModule) this.getClass().getAnnotation(DependentModule.class);
		if(dependentModule == null) return null;
		ArrayList<LongerModule> list = new ArrayList<LongerModule>();
		for(Class<?> moduleClass : dependentModule.value()){
			list.add((LongerModule)(moduleClass.newInstance()));
		}
		LongerModule[] modules = new LongerModule[list.size()];
		return list.toArray(modules);
	}
	
	public LongerModule[] getDependentOnMeModules(){
		
		return null;
	}
	
	protected abstract boolean isInstalled() throws Exception;
	protected abstract void install() throws Exception;
	protected abstract void uninstall() throws Exception;
	protected abstract void update() throws Exception;
	protected abstract void init() throws Exception;
	
	@Override
	public String toString() {
		return this.getModuleName();
	}
	
	public List<Class<?>> getModuleClasses(){
		Class<?>[] classes = this.getClasses();
		if(classes == null){
			try {
				return ReflectionHelper.getClasses(this.getClass().getPackage());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}else{
			ArrayList<Class<?>> list = new ArrayList<Class<?>>();
			for(Class<?> clazz : classes){
				list.add(clazz);
			}
			return list;
		}
	}
	
	public Class<?>[] getClasses(){
		return null;
	}
}
